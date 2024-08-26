package com.petkanov.webfluxpatterns.p7.retry.service;

import com.petkanov.webfluxpatterns.p7.retry.client.ProductClient;
import com.petkanov.webfluxpatterns.p7.retry.client.ReviewClient;
import com.petkanov.webfluxpatterns.p7.retry.dto.Product;
import com.petkanov.webfluxpatterns.p7.retry.dto.ProductAggregate;
import com.petkanov.webfluxpatterns.p7.retry.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductAggregatorService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ReviewClient reviewClient;

    public Mono<ProductAggregate> aggregate(Integer id){
        return Mono.zip(
               this.productClient.getProduct(id),
               this.reviewClient.getReviews(id)
        )
        .map(t -> toDto(t.getT1(), t.getT2()));
    }

    private ProductAggregate toDto(Product product, List<Review> reviews){
        return ProductAggregate.create(
                product.getId(),
                product.getCategory(),
                product.getDescription(),
                reviews
        );
    }


}

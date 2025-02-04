package com.petkanov.webfluxpatterns.p7.retry.client;

import com.petkanov.webfluxpatterns.p7.retry.dto.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

@Service
public class ReviewClient {

    private final WebClient client;

    public ReviewClient(@Value("${sec07.review.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Mono<List<Review>>  getReviews(Integer id){
        return this.client
                .get()
                .uri("{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.empty())
                .bodyToFlux(Review.class)
                .collectList()
                .retry(5)
//                .retryWhen(Retry.fixedDelay(5, Duration.ofMillis(100)))  // 5 attempts with 100ms delay in between
                .timeout(Duration.ofMillis(300))
                .onErrorReturn(Collections.emptyList());
    }

}

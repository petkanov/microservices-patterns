package com.petkanov.webfluxpatterns.p9.ratelimiter.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Product {

    private Integer id;
    private String category;
    private String description;
    private Integer price;

}

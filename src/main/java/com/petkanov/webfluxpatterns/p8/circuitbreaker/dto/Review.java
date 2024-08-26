package com.petkanov.webfluxpatterns.p8.circuitbreaker.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Review {

    private Integer id;
    private String user;
    private Integer rating;
    private String comment;

}

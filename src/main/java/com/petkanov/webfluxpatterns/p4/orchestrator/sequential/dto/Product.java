package com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Product {

    private Integer id;
    private String category;
    private String description;
    private Integer price;

}

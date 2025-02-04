package com.petkanov.webfluxpatterns.p1.aggregator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class PromotionResponse {

    private Integer id;
    private String type;
    private Double discount;
    private LocalDate endDate;

}

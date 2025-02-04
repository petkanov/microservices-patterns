package com.petkanov.webfluxpatterns.p3.orchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class PaymentResponse {

    private Integer userId;
    private String name;
    private Integer balance;
    private Status status;

}

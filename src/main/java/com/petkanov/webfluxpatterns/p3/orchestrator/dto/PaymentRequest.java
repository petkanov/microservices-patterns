package com.petkanov.webfluxpatterns.p3.orchestrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class PaymentRequest {

    private Integer userId;
    private Integer amount;
    private UUID orderId;

}

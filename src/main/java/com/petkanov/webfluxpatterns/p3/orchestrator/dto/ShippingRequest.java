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
public class ShippingRequest {

    private Integer quantity;
    private Integer userId;
    private UUID orderId;

}

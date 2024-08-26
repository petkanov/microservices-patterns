package com.petkanov.webfluxpatterns.p4.orchestrator.sequential.controller;

import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.OrderRequest;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.OrderResponse;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sec04")
public class OrderController {

    @Autowired
    private OrchestratorService service;

    @PostMapping("order")
    public Mono<ResponseEntity<OrderResponse>> placeOrder(@RequestBody Mono<OrderRequest> mono){
        return this.service.placeOrder(mono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}

package com.petkanov.webfluxpatterns.p4.orchestrator.sequential.service;

import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.client.InventoryClient;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.OrchestrationRequestContext;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class InventoryOrchestrator extends Orchestrator {

    @Autowired
    private InventoryClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.deduct(ctx.getInventoryRequest())
                .doOnNext(ctx::setInventoryResponse)
                .thenReturn(ctx)
                .handle(this.statusHandler());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Objects.nonNull(ctx.getInventoryResponse()) && Status.SUCCESS.equals(ctx.getInventoryResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getInventoryRequest)
                .flatMap(this.client::restore)
                .subscribe();
    }
}

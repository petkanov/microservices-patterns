package com.petkanov.webfluxpatterns.p4.orchestrator.sequential.service;

import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.client.ShippingClient;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.OrchestrationRequestContext;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@Service
public class ShippingOrchestrator extends Orchestrator {

    @Autowired
    private ShippingClient client;

    @Override
    public Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx) {
        return this.client.schedule(ctx.getShippingRequest())
                .doOnNext(ctx::setShippingResponse)
                .thenReturn(ctx)
                .handle(this.statusHandler());
    }

    @Override
    public Predicate<OrchestrationRequestContext> isSuccess() {
        return ctx -> Objects.nonNull(ctx.getShippingResponse()) && Status.SUCCESS.equals(ctx.getShippingResponse().getStatus());
    }

    @Override
    public Consumer<OrchestrationRequestContext> cancel() {
        return ctx -> Mono.just(ctx)
                .filter(isSuccess())
                .map(OrchestrationRequestContext::getShippingRequest)
                .flatMap(this.client::cancel)
                .subscribe();
    }
}

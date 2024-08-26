package com.petkanov.webfluxpatterns.p4.orchestrator.sequential.service;

import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.exception.OrderFulfillmentFailure;
import com.petkanov.webfluxpatterns.p4.orchestrator.sequential.dto.OrchestrationRequestContext;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SynchronousSink;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public abstract class Orchestrator {

    public abstract Mono<OrchestrationRequestContext> create(OrchestrationRequestContext ctx);
    public abstract Predicate<OrchestrationRequestContext> isSuccess();
    public abstract Consumer<OrchestrationRequestContext> cancel();

    protected BiConsumer<OrchestrationRequestContext, SynchronousSink<OrchestrationRequestContext>> statusHandler(){
        return (ctx, sink) -> {
            if(isSuccess().test(ctx)){
                sink.next(ctx);
            }else{
                sink.error(new OrderFulfillmentFailure());
            }
        };
    }



}

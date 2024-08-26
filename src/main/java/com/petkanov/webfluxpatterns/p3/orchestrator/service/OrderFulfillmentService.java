package com.petkanov.webfluxpatterns.p3.orchestrator.service;

import com.petkanov.webfluxpatterns.p3.orchestrator.dto.OrchestrationRequestContext;
import com.petkanov.webfluxpatterns.p3.orchestrator.dto.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderFulfillmentService {

    @Autowired
    private List<Orchestrator> orchestrators;

    public Mono<OrchestrationRequestContext> placeOrder(OrchestrationRequestContext ctx) {

        List<Mono<OrchestrationRequestContext>> list = orchestrators.stream()
                .map(o -> o.create(ctx))
                .collect(Collectors.toList());

        return Mono.zip(list, a -> a[0])
                .cast(OrchestrationRequestContext.class)
                .doOnNext(this::updateStatus);
    }

    private void updateStatus(OrchestrationRequestContext ctx) {
        var allSuccess = this.orchestrators.stream().allMatch(o -> o.isSuccess().test(ctx));
        var status = allSuccess ? Status.SUCCESS : Status.FAILED;
        ctx.setStatus(status);
    }

}

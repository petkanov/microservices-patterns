package com.petkanov.webfluxpatterns.p5.splitter.service;

import com.petkanov.webfluxpatterns.p5.splitter.dto.ReservationItemRequest;
import com.petkanov.webfluxpatterns.p5.splitter.dto.ReservationItemResponse;
import com.petkanov.webfluxpatterns.p5.splitter.dto.ReservationType;
import reactor.core.publisher.Flux;

public abstract class ReservationHandler {

    protected abstract ReservationType getType();
    protected abstract Flux<ReservationItemResponse> reserve(Flux<ReservationItemRequest> flux);

}

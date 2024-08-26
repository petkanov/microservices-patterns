package com.petkanov.webfluxpatterns.p5.splitter.controller;

import com.petkanov.webfluxpatterns.p5.splitter.service.ReservationService;
import com.petkanov.webfluxpatterns.p5.splitter.dto.ReservationItemRequest;
import com.petkanov.webfluxpatterns.p5.splitter.dto.ReservationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("sec05")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping("reserve")
    public Mono<ReservationResponse> reserve(@RequestBody Flux<ReservationItemRequest> flux){
        return this.service.reserve(flux);
    }

}

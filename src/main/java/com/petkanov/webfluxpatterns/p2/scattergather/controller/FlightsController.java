package com.petkanov.webfluxpatterns.p2.scattergather.controller;

import com.petkanov.webfluxpatterns.p2.scattergather.service.FlightSearchService;
import com.petkanov.webfluxpatterns.p2.scattergather.dto.FlightResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("scattergather")
public class FlightsController {

    @Autowired
    private FlightSearchService service;

    @GetMapping(value = "flights/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<FlightResult> getFlights(@PathVariable String from, @PathVariable String to){
        return this.service.getFlights(from, to);
    }

}

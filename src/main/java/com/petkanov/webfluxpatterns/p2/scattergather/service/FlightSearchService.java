package com.petkanov.webfluxpatterns.p2.scattergather.service;

import com.petkanov.webfluxpatterns.p2.scattergather.client.JetBlueClient;
import com.petkanov.webfluxpatterns.p2.scattergather.dto.FlightResult;
import com.petkanov.webfluxpatterns.p2.scattergather.client.DeltaClient;
import com.petkanov.webfluxpatterns.p2.scattergather.client.FrontierClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class FlightSearchService {

    @Autowired
    private DeltaClient deltaClient;

    @Autowired
    private FrontierClient frontierClient;

    @Autowired
    private JetBlueClient jetBlueClient;

    public Flux<FlightResult> getFlights(String from, String to){
        return Flux.merge(
                this.deltaClient.getFlights(from, to),
                this.frontierClient.getFlights(from, to),
                this.jetBlueClient.getFlights(from, to)
        )
        .take(Duration.ofSeconds(3));
    }

}

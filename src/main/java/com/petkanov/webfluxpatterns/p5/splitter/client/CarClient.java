package com.petkanov.webfluxpatterns.p5.splitter.client;

import com.petkanov.webfluxpatterns.p5.splitter.dto.CarReservationRequest;
import com.petkanov.webfluxpatterns.p5.splitter.dto.CarReservationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CarClient {

    private final WebClient client;

    public CarClient(@Value("${sec05.car.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Flux<CarReservationResponse> reserve(Flux<CarReservationRequest> flux){
        return this.client
                .post()
                .body(flux, CarReservationRequest.class)
                .retrieve()
                .bodyToFlux(CarReservationResponse.class)
                .onErrorResume(ex -> Mono.empty());
    }

}

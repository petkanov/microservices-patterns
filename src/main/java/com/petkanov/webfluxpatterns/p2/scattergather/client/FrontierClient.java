package com.petkanov.webfluxpatterns.p2.scattergather.client;

import com.petkanov.webfluxpatterns.p2.scattergather.dto.FlightResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FrontierClient {

    private final WebClient client;

    public FrontierClient(@Value("${scattergather.frontier.service}") String baseUrl){
        this.client = WebClient.builder()
                               .baseUrl(baseUrl)
                               .build();
    }

    public Flux<FlightResult> getFlights(String from, String to){
        return this.client
                .post()
                .bodyValue(FrontierRequest.create(from, to))
                .retrieve()
                .bodyToFlux(FlightResult.class)
                .onErrorResume(ex -> Mono.empty());
    }

    @Data
    @ToString
    @AllArgsConstructor(staticName = "create")
    private static class FrontierRequest{
        private String from;
        private String to;
    }

}

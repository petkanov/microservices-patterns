package com.petkanov.webfluxpatterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.petkanov.webfluxpatterns.sec10")
public class WebfluxPatternsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxPatternsApplication.class, args);
	}
}

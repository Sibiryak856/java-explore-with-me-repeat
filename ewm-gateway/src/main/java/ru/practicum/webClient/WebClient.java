package ru.practicum.webClient;

import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClient {

    protected final org.springframework.web.reactive.function.client.WebClient webClient;


    public WebClient(org.springframework.web.reactive.function.client.WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("${api.base.url}").build();
    }
}

package ru.practicum.webClient;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class BaseClient {

    protected final WebClient webClient;

    public BaseClient(WebClient.Builder webClient) {
        this.webClient = webClient.baseUrl("${api.base.url}").build();
    }
}

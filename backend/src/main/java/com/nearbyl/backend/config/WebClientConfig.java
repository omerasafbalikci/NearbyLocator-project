package com.nearbyl.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient locationIqClient() {
        return WebClient.builder()
                .baseUrl("https://api.locationiq.com")
                .build();
    }
}

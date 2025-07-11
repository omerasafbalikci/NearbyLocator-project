package com.nearbyl.backend.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "locationiq")
public record LocationIqApiProperties(String apiKey) {}
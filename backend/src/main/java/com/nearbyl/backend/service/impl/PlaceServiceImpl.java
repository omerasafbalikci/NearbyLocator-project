package com.nearbyl.backend.service.impl;

import com.nearbyl.backend.config.GoogleApiProperties;
import com.nearbyl.backend.dto.GooglePlacesResponse;
import com.nearbyl.backend.dto.PlaceDTO;
import com.nearbyl.backend.entity.PlaceEntity;
import com.nearbyl.backend.repository.PlaceRepository;
import com.nearbyl.backend.utilities.PlaceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl {

    private static final Duration TTL = Duration.ofHours(12);

    private final PlaceRepository repo;
    private final WebClient googleClient;
    private final GoogleApiProperties props;

    public List<PlaceDTO> findNearby(double lat, double lng, int radius) {
        String hash = hash(lat, lng, radius);

        List<PlaceEntity> cached = repo.findByQueryHash(hash);
        if (!cached.isEmpty() &&
                cached.get(0).getCachedAt().isAfter(Instant.now().minus(TTL))) {
            return cached.stream().map(PlaceDTO::fromEntity).toList();
        }

        GooglePlacesResponse response = googleClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/maps/api/place/nearbysearch/json")
                        .queryParam("location", lat + "," + lng)
                        .queryParam("radius", radius)
                        .queryParam("key", props.apiKey())
                        .build())
                .retrieve()
                .bodyToMono(GooglePlacesResponse.class)
                .block();

        if (!"OK".equals(Objects.requireNonNull(response).status())) {
            throw new IllegalStateException("Google Places hatası: " + response.status()
                    + " - " + response.errorMessage());
        }

        List<PlaceEntity> entities = response.results().stream()
                .map(r -> PlaceEntityMapper.toEntity(r, hash))
                .toList();
        repo.saveAll(entities);
        return entities.stream().map(PlaceDTO::fromEntity).toList();
    }

    private String hash(double lat, double lng, int radius) {
        return String.format("%.4f_%.4f_%d", lat, lng, radius);
    }
}
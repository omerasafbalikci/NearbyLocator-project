package com.nearbyl.backend.service.impl;

import com.nearbyl.backend.config.LocationIqApiProperties;
import com.nearbyl.backend.dto.LocationIqPlace;
import com.nearbyl.backend.dto.PlaceDTO;
import com.nearbyl.backend.entity.PlaceEntity;
import com.nearbyl.backend.repository.PlaceRepository;
import com.nearbyl.backend.service.abstracts.PlaceService;
import com.nearbyl.backend.utilities.PlaceEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private static final Duration TTL = Duration.ofHours(12);

    private final PlaceRepository repo;
    private final WebClient locationIqClient;
    private final LocationIqApiProperties props;

    @Override
    public List<PlaceDTO> findNearby(double lat, double lng, int radius) {
        String hash = hash(lat, lng, radius);

        var cached = repo.findByQueryHash(hash);
        if (!cached.isEmpty() &&
                cached.get(0).getCachedAt().isAfter(Instant.now().minus(TTL))) {
            return cached.stream().map(PlaceDTO::fromEntity).toList();
        }

        // LocationIQ call
        var url = "/v1/nearby.php?key=%s&lat=%f&lon=%f&radius=%d&format=json"
                .formatted(props.apiKey(), lat, lng, radius);

        LocationIqPlace[] resp = locationIqClient.get().uri(url)
                .retrieve().bodyToMono(LocationIqPlace[].class).block();

        List<PlaceEntity> entities = Arrays.stream(resp)
                .map(p -> PlaceEntityMapper.from(p, hash)).toList();

        repo.saveAll(entities);
        return entities.stream().map(PlaceDTO::fromEntity).toList();
    }

    private String hash(double lat, double lng, int radius) {
        return String.format("%.4f_%.4f_%d", lat, lng, radius);
    }
}
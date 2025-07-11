package com.nearbyl.backend.utilities;

import com.nearbyl.backend.dto.LocationIqPlace;
import com.nearbyl.backend.entity.PlaceEntity;

import java.time.Instant;

public final class PlaceEntityMapper {
    private PlaceEntityMapper() {
    }

    public static PlaceEntity from(LocationIqPlace p, String hash) {
        return PlaceEntity.builder()
                .placeId(String.valueOf(p.placeId()))
                .name(p.name())
                .lat(Double.parseDouble(p.lat()))
                .lng(Double.parseDouble(p.lon()))
                .address(p.name())
                .types(p.type())
                .queryHash(hash)
                .cachedAt(Instant.now())
                .build();
    }
}

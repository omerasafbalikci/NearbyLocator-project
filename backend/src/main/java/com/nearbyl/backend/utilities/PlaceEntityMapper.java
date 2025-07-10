package com.nearbyl.backend.utilities;

import com.nearbyl.backend.dto.GooglePlacesResponse;
import com.nearbyl.backend.entity.PlaceEntity;

import java.time.Instant;
import java.util.StringJoiner;

public final class PlaceEntityMapper {
    private PlaceEntityMapper() {
    }

    public static PlaceEntity toEntity(GooglePlacesResponse.Result r, String hash) {
        StringJoiner joiner = new StringJoiner(",");
        r.types().forEach(joiner::add);

        return PlaceEntity.builder()
                .placeId(r.placeId())
                .name(r.name())
                .lat(r.geometry().location().lat())
                .lng(r.geometry().location().lng())
                .address(r.vicinity())
                .rating(r.rating())
                .types(joiner.toString())
                .iconUrl(r.icon())
                .queryHash(hash)
                .cachedAt(Instant.now())
                .build();
    }
}

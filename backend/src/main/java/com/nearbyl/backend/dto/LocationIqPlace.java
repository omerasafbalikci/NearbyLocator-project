package com.nearbyl.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LocationIqPlace(
        @JsonProperty("place_id") long placeId,
        @JsonProperty("osm_id") long osmId,
        @JsonProperty("lat") String lat,
        @JsonProperty("lon") String lon,
        @JsonProperty("display_name") String name,
        @JsonProperty("type") String type,
        double importance
) {
}

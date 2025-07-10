package com.nearbyl.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GooglePlacesResponse(
        @JsonProperty("results") List<Result> results,
        @JsonProperty("status") String status,
        @JsonProperty("error_message") String errorMessage
) {
    public record Result(
            @JsonProperty("place_id") String placeId,
            @JsonProperty("name") String name,
            @JsonProperty("vicinity") String vicinity,
            @JsonProperty("rating") Double rating,
            @JsonProperty("types") List<String> types,
            @JsonProperty("icon") String icon,
            @JsonProperty("geometry") Geometry geometry
    ) {
        public record Geometry(
                @JsonProperty("location") Location location
        ) {
            public record Location(
                    @JsonProperty("lat") double lat,
                    @JsonProperty("lng") double lng
            ) {
            }
        }
    }
}
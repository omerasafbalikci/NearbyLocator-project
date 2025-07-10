package com.nearbyl.backend.dto;

import com.nearbyl.backend.entity.PlaceEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class PlaceDTO {
    private String placeId;
    private String name;
    private double lat;
    private double lng;
    private String address;
    private Double rating;
    private List<String> types;
    private String iconUrl;

    public static PlaceDTO fromEntity(PlaceEntity e) {
        return PlaceDTO.builder()
                .placeId(e.getPlaceId())
                .name(e.getName())
                .lat(e.getLat())
                .lng(e.getLng())
                .address(e.getAddress())
                .rating(e.getRating())
                .types(e.getTypes() == null ? List.of() : Arrays.asList(e.getTypes().split(",")))
                .iconUrl(e.getIconUrl())
                .build();
    }
}

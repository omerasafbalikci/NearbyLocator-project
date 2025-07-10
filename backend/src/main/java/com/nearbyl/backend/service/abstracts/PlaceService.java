package com.nearbyl.backend.service.abstracts;

import com.nearbyl.backend.dto.PlaceDTO;

import java.util.List;

public interface PlaceService {

    List<PlaceDTO> findNearby(double lat, double lng, int radius);
}

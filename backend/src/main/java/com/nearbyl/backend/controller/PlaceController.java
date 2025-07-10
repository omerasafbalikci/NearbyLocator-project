package com.nearbyl.backend.controller;

import com.nearbyl.backend.dto.PlaceDTO;
import com.nearbyl.backend.service.abstracts.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService service;

    @GetMapping("/places")
    public ResponseEntity<List<PlaceDTO>> getNearby(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "500") int radius) {
        return ResponseEntity.ok(service.findNearby(lat, lng, radius));
    }
}
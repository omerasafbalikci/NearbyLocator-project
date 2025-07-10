package com.nearbyl.backend.repository;

import com.nearbyl.backend.entity.PlaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<PlaceEntity, Long> {

    List<PlaceEntity> findByQueryHash(String hash);
}

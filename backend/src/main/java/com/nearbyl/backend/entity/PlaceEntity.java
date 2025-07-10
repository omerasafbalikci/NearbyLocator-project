package com.nearbyl.backend.entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.Instant;

@Entity
@Table(name = "places", indexes = {
        @Index(name = "idx_placeid", columnList = "placeId", unique = true)
})
@Builder
public class PlaceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placeId;
    private String name;
    private Double lat;
    private Double lng;
    private String address;
    private Double rating;
    @Column(length = 512)
    private String types;
    private String iconUrl;
    private String queryHash;
    private Instant cachedAt;

    public PlaceEntity() {
    }

    public PlaceEntity(Long id, String placeId, String name, Double lat, Double lng, String address, Double rating, String types, String iconUrl, String queryHash, Instant cachedAt) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.rating = rating;
        this.types = types;
        this.iconUrl = iconUrl;
        this.queryHash = queryHash;
        this.cachedAt = cachedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getQueryHash() {
        return queryHash;
    }

    public void setQueryHash(String queryHash) {
        this.queryHash = queryHash;
    }

    public Instant getCachedAt() {
        return cachedAt;
    }

    public void setCachedAt(Instant cachedAt) {
        this.cachedAt = cachedAt;
    }
}

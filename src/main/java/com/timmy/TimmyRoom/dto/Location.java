package com.timmy.TimmyRoom.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Location {
    private Double latitude;
    private Double longitude;

    @Builder
    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

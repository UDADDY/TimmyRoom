package com.timmy.TimmyRoom.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class RedisDTO {
    private final String key;
    private final String value;
    private final Duration duration;
}

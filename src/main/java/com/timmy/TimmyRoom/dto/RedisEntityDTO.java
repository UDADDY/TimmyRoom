package com.timmy.TimmyRoom.dto;

import lombok.Data;

import java.time.Duration;

@Data
public class RedisEntityDTO {
    private final Long id;
    private final String name;
}

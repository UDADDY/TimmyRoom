package com.timmy.TimmyRoom.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "redisEntity", timeToLive = 300)
public class RedisEntity {

    @Id
    public Long id;
    public String name;

    @Builder
    public RedisEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

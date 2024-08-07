package com.timmy.TimmyRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private User user;

    @Builder
    public RedisEntity(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}

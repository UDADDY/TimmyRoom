package com.timmy.TimmyRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@RedisHash(value = "redisEntity", timeToLive = 300)
public class RedisEntity implements Serializable {

    @Id
    public Long id;
    public String name;
    private User user;

    @Builder
    public RedisEntity(Long id, String name, User user) {
        this.id = id;
        this.name = name;
        this.user = user;
    }
}

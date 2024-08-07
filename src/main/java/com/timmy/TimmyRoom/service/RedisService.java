package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.dto.request.RedisEntityRequestDTO;
import com.timmy.TimmyRoom.entity.RedisEntity;
import org.springframework.stereotype.Service;

@Service
public interface RedisService {
    RedisEntity findById(Long id);

    RedisEntity save(RedisEntityRequestDTO redisEntityRequestDTO, String email);

    void deleteById(Long id, String email);
}

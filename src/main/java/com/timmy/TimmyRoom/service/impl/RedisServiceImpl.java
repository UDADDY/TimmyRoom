package com.timmy.TimmyRoom.service.impl;

import com.timmy.TimmyRoom.dto.request.RedisEntityRequestDTO;
import com.timmy.TimmyRoom.entity.RedisEntity;
import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.excpetion.RedisEntityNotFound;
import com.timmy.TimmyRoom.repository.RedisEntityRepository;
import com.timmy.TimmyRoom.service.RedisService;
import com.timmy.TimmyRoom.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisEntityRepository redisEntityRepository;
    private final UserService userService;

    @Override
    public RedisEntity findById(Long id) {
        return redisEntityRepository.findById(id).orElseThrow(() -> new RedisEntityNotFound());
    }

    @Override
    public RedisEntity save(RedisEntityRequestDTO redisEntityRequestDTO, String email) {
        User user = userService.findUserByEmail(email);
        RedisEntity redisEntity = RedisEntity.builder()
                .id(redisEntityRequestDTO.getId())
                .name(redisEntityRequestDTO.getName())
                .user(user)
                .build();

        return redisEntityRepository.save(redisEntity);
    }

    @Override
    public void deleteById(Long id) {
        redisEntityRepository.deleteById(id);
    }
}

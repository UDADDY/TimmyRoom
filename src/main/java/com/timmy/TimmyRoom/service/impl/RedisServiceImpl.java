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


    /**
     * Redis 값을 등록/수정합니다.
     *
     * @param {String} key : redis key
     * @param {Stirng} value : redis value
     * @return {void}
     */
    @Override
    public void setValues(String key, String value) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, value);
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

    /**
     * Redis 키를 기반으로 값을 조회합니다.
     * 
     * @param {String} key : redis key
     * @return {String} reids value 값 반환 or 미존재 시 빈 값 반환
     */
    @Override
    public String getValue(String key) {
        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        if(values.get(key) == null)
            return "";

        return String.valueOf(values.get(key));
    }

    /**
     * Redis 키 값을 기반으로 row 삭제합니다.
     *
     * @param {String} key : redis key
     * @return
     */
    @Override
    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}

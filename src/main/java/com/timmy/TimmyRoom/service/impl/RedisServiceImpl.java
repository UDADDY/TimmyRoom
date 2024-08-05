package com.timmy.TimmyRoom.service.impl;

import com.timmy.TimmyRoom.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, Object> redisTemplate;


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

    /**
     * Redis 값을 등록/수정합니다.
     *
     * @param {String} key : redis key
     * @param {Stirng} value : redis value
     * @param {Duration} duration: redis 값 메모리 상의 유효시간
     * @return {void}
     */
    @Override
    public void setValues(String key, String value, Duration duration) {
        if(duration == null) {
            setValues(key, value);
            return;
        }

        ValueOperations<String, Object> values = redisTemplate.opsForValue();
        values.set(key, value, duration);
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

package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.RedisEntityRequestDTO;
import com.timmy.TimmyRoom.entity.RedisEntity;
import com.timmy.TimmyRoom.repository.RedisEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/redis")
public class RedisTestController {

//    private final RedisService redisService;
    private final RedisEntityRepository redisEntityRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getValue(@PathVariable Long id){
        RedisEntity redisEntity = redisEntityRepository.findById(id).get();

        return ResponseEntity.ok(redisEntity);
    }

    @PostMapping
    public ResponseEntity<?> setValue(@RequestBody RedisEntityRequestDTO redisEntityRequestDTO){
        RedisEntity redisEntity = RedisEntity.builder()
                .id(redisEntityRequestDTO.getId())
                .name(redisEntityRequestDTO.getName())
                .build();

        RedisEntity saved = redisEntityRepository.save(redisEntity);

        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRow(@PathVariable Long id){
        redisEntityRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

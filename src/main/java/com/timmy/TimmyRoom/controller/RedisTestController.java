package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.RedisDTO;
import com.timmy.TimmyRoom.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/redis")
public class RedisTestController {

    private final RedisService redisService;

    @GetMapping("/{key}")
    public ResponseEntity<?> getValue(@PathVariable String key){
        String value = redisService.getValue(key);

        return ResponseEntity.ok(value);
    }

    @PostMapping
    public ResponseEntity<?> setValue(@RequestBody RedisDTO redisDTO){
        int reulst = 0;

        redisService.setValues(redisDTO.getKey(), redisDTO.getValue(), redisDTO.getDuration());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<?> deleteRow(@PathVariable String key){
        redisService.deleteValue(key);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

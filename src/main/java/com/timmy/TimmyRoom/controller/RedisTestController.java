package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.RedisEntityRequestDTO;
import com.timmy.TimmyRoom.entity.RedisEntity;
import com.timmy.TimmyRoom.gloabl.error.ErrorResponse;
import com.timmy.TimmyRoom.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/redis")
@Tag(name = "레디스")
@RequiredArgsConstructor
public class RedisTestController {

    private final RedisService redisService;

    @GetMapping("/{id}")
    @Operation(summary = "레디스 엔티티 조회")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "조회 성공",
                            responseCode = "200",
                            content = {@Content(schema = @Schema(implementation = RedisEntity.class))}
                    ),
                    @ApiResponse(
                            description = "사용자 인증 실패",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            description = "조회 실패",
                            responseCode = "404",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    public ResponseEntity<?> getValue(@PathVariable("id") Long id){
        RedisEntity redisEntity = redisService.findById(id);

        return ResponseEntity.ok(redisEntity);
    }

    @PostMapping
    @Operation(summary = "레디스 엔티티 생성")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "생성 성공",
                            responseCode = "201",
                            content = {@Content(schema = @Schema(implementation = RedisEntity.class))}
                    ),
                    @ApiResponse(
                            description = "사용자 인증 실패",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    public ResponseEntity<?> setValue(@AuthenticationPrincipal UserDetails userDetails, @RequestBody RedisEntityRequestDTO redisEntityRequestDTO){
        RedisEntity redisEntity = redisService.save(redisEntityRequestDTO, userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(redisEntity);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "레디스 엔티티 삭제")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            description = "삭제 성공",
                            responseCode = "204"
                    ),
                    @ApiResponse(
                            description = "사용자 인증 실패",
                            responseCode = "401",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    public ResponseEntity<?> deleteRow(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id){
        redisService.deleteById(id, userDetails.getUsername());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

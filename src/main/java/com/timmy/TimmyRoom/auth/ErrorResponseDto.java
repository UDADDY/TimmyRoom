package com.timmy.TimmyRoom.auth;

import java.time.LocalDateTime;

public class ErrorResponseDto {
    private final int status;
    private final String message;
    private final LocalDateTime time;

    public ErrorResponseDto(int status, String message, LocalDateTime time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }
}

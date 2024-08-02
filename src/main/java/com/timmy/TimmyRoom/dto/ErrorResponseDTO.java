package com.timmy.TimmyRoom.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private final int status;
    private final String message;
    private final LocalDateTime time;

    public ErrorResponseDTO(int status, String message, LocalDateTime time) {
        this.status = status;
        this.message = message;
        this.time = time;
    }
}

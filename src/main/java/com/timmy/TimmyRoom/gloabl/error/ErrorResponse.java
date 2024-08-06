package com.timmy.TimmyRoom.gloabl.error;

import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;
import lombok.Data;

@Data
public class ErrorResponse {
    private final String code;
    private final String message;
    private final Integer status;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}

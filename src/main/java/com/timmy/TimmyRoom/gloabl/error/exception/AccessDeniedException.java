package com.timmy.TimmyRoom.gloabl.error.exception;

import lombok.Getter;

@Getter
public class AccessDeniedException extends RuntimeException{
    private final ErrorCode errorCode;

    public AccessDeniedException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public AccessDeniedException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

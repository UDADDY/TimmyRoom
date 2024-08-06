package com.timmy.TimmyRoom.gloabl.error.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends BusinessException{

    public EntityNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

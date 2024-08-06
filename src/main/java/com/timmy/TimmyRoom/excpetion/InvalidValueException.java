package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.BusinessException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class InvalidValueException extends BusinessException {

    public InvalidValueException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public InvalidValueException(ErrorCode errorCode) {
        super(errorCode);
    }
}

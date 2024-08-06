package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.EntityNotFoundException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}

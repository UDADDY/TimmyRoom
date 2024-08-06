package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.EntityNotFoundException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class FileNotFoundException extends EntityNotFoundException {
    public FileNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
}

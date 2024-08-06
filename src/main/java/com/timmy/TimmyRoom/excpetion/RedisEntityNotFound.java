package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.EntityNotFoundException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;

public class RedisEntityNotFound extends EntityNotFoundException {
    public RedisEntityNotFound() {
        super(ErrorCode.REDIS_ENTITY_NOT_FOUND);
    }
}

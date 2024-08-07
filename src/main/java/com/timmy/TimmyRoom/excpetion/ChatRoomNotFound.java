package com.timmy.TimmyRoom.excpetion;

import com.timmy.TimmyRoom.gloabl.error.exception.EntityNotFoundException;
import com.timmy.TimmyRoom.gloabl.error.exception.ErrorCode;
import lombok.Data;

@Data
public class ChatRoomNotFound extends EntityNotFoundException {
    public ChatRoomNotFound() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
}

package com.timmy.TimmyRoom.dto;

import com.timmy.TimmyRoom.entity.MessageType;
import lombok.Data;

@Data
public class ChatMessageDTO {
    private MessageType messageType;
    private Long chatRoomId;
    private String sender;
    private String message;
}
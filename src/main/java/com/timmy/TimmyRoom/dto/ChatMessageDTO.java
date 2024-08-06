package com.timmy.TimmyRoom.dto;

import lombok.Data;

@Data
public class ChatMessageDTO {
    private MessageType messageType;
    private String chatRoomId;
    private String sender;
    private String message;
}
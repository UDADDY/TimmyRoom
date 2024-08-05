package com.timmy.TimmyRoom.dto;

import lombok.Data;

@Data
public class ChatRoomCreateRequestDTO {
    private final String name;
    private final String email;
}

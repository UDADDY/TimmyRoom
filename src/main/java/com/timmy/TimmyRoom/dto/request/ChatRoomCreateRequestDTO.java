package com.timmy.TimmyRoom.dto.request;

import lombok.Data;

@Data
public class ChatRoomCreateRequestDTO {
    private final String name;
    private final String email;
}

package com.timmy.TimmyRoom.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatRoomCreateRequestDTO {
    @Schema(example = "example-of-chatRoomId")
    private String chatRoomName;
}

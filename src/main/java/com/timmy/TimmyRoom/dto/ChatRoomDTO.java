package com.timmy.TimmyRoom.dto;

import com.timmy.TimmyRoom.entity.ChatRoom;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ChatRoomDTO {
    @Schema(example = "example-of-chatRoomId")
    private String roomId;

    @Schema(example = "선린찐따들")
    private String name;

    @Schema(example = "gustmd5715@gmail.com")
    private String host;

    public static ChatRoomDTO fromEntity(ChatRoom chatRoom){
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.roomId = chatRoom.getRoomId();
        chatRoomDTO.name = chatRoom.getName();
        chatRoomDTO.host = chatRoom.getHost();

        return chatRoomDTO;
    }
}
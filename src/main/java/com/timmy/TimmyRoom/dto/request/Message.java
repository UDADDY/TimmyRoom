package com.timmy.TimmyRoom.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String role;
    private String content;

    @Builder
    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }
}

package com.timmy.TimmyRoom.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChatCompletionDTO {
    private String model;
    private List<Message> messages;

    @Builder
    public ChatCompletionDTO(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}

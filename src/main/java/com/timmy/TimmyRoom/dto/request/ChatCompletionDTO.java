package com.timmy.TimmyRoom.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
public class ChatCompletionDTO {
    private String model;
    private String prompt;
    private float temperature;

    @Builder
    public ChatCompletionDTO(String model, String prompt, float temperature) {
        this.model = model;
        this.prompt = prompt;
        this.temperature = temperature;
    }
}

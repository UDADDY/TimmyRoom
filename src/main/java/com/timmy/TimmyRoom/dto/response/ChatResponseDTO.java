package com.timmy.TimmyRoom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponseDTO {
    private String id;
    private String object;
    private String created;
    private String model;
    private String systemFingerprint;

    private List<Choice> choices;
}

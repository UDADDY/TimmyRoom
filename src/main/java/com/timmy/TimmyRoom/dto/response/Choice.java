package com.timmy.TimmyRoom.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Choice {
    private Integer index;
    private String finishReason;
    private Message message;
}

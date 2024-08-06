package com.timmy.TimmyRoom.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RedisEntityRequestDTO {
    @Schema(example = "1")
    private final Long id;

    @Schema(example = "anything")
    private final String name;
}

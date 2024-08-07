package com.timmy.TimmyRoom.service;

import com.timmy.TimmyRoom.dto.request.ChatCompletionDTO;

import java.util.Map;

public interface ChatGPTService {
    Map<String, Object> prompt(ChatCompletionDTO chatCompletionDTO);
}

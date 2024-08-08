package com.timmy.TimmyRoom.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.config.ChatGPTConfig;
import com.timmy.TimmyRoom.dto.request.AskRequestDTO;
import com.timmy.TimmyRoom.dto.request.ChatCompletionDTO;
import com.timmy.TimmyRoom.dto.request.Message;
import com.timmy.TimmyRoom.dto.response.ChatResponseDTO;
import com.timmy.TimmyRoom.dto.response.Choice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.prompt}")
    private String promptUrl;

    public List<Choice> prompt(AskRequestDTO askRequestDTO) {
        Map<String, Object> resultMap = new HashMap<>();

        HttpHeaders headers = chatGPTConfig.httpHeaders();
        ChatCompletionDTO chatCompletionDTO = ChatCompletionDTO.builder()
                .model(model)
                .messages(List.of(
                        new Message("system", "You are a helpful assistant"),
                        new Message("user", askRequestDTO.getQuestion())
                        )
                ).build();

        HttpEntity<ChatCompletionDTO> requestEntity = new HttpEntity<>(chatCompletionDTO, headers);
        ResponseEntity<ChatResponseDTO> response = restTemplate.exchange(
                promptUrl,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<ChatResponseDTO>(){}
        );

        ChatResponseDTO contents = response.getBody();
        List<Choice> choices = contents.getChoices();

        return choices;
    }
}

package com.timmy.TimmyRoom.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timmy.TimmyRoom.config.ChatGPTConfig;
import com.timmy.TimmyRoom.dto.request.ChatCompletionDTO;
import com.timmy.TimmyRoom.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatGPTServiceImpl implements ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;
    private final ObjectMapper mapper;
    private final RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.prompt}")
    private String promptUrl;

    @Override
    public Map<String, Object> prompt(ChatCompletionDTO chatCompletionDTO) {
        log.debug("[+] 프롬프트를 수행합니다.");

        Map<String, Object> resultMap = new HashMap<>();

        HttpHeaders headers = chatGPTConfig.httpHeaders();
        HttpEntity<ChatCompletionDTO> requestEntity = new HttpEntity<>(chatCompletionDTO, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                promptUrl,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        try {
            resultMap = mapper.readValue(response.getBody(), new TypeReference<>(){});
        } catch (JsonProcessingException e){
            log.debug("JsonProcessingException" + e.getMessage());
        } catch (RuntimeException e){
            log.debug("RuntimeException" + e.getMessage());
        }

        return resultMap;
    }
}

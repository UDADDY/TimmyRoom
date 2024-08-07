package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.ChatCompletionDTO;
import com.timmy.TimmyRoom.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/chatGpt")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public ResponseEntity<Map<String, Object>> selectPrompt(@RequestBody ChatCompletionDTO chatCompletionDTO){
        Map<String, Object> result = chatGPTService.prompt(chatCompletionDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

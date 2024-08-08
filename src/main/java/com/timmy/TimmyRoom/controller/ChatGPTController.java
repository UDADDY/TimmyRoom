package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.AskRequestDTO;
import com.timmy.TimmyRoom.dto.response.Choice;
import com.timmy.TimmyRoom.service.ChatGPTService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/chatGpt")
@RequiredArgsConstructor
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/prompt")
    public ResponseEntity<List<Choice>> selectPrompt(@RequestBody AskRequestDTO askRequestDTO){
        List<Choice> result = chatGPTService.prompt(askRequestDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

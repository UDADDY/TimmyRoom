package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.dto.request.FcmSendDTO;
import com.timmy.TimmyRoom.service.FcmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
@Slf4j
public class FcmController {
    private final FcmService fcmService;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody @Valid FcmSendDTO fcmSendDTO) throws IOException {
        int result = fcmService.sendMessageTo(fcmSendDTO);

        return ResponseEntity.ok(result);
    }

    @PostMapping("/send/v2")
    public ResponseEntity<?> sendMessageUsingSDK(@RequestBody @Valid FcmSendDTO fcmSendDTO) throws IOException {
        String result = fcmService.sendMessage(fcmSendDTO);

        return ResponseEntity.ok(result);
    }
}

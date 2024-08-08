package com.timmy.TimmyRoom.controller;

import com.timmy.TimmyRoom.service.AwsMailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
@Tag(name = "메일")
public class MailController {
    private final AwsMailService awsMailService;

    @PostMapping
    public ResponseEntity<?> sendMail(){
        awsMailService.send();

        return ResponseEntity.ok().build();
    }
}

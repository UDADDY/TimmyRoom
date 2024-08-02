package com.timmy.TimmyRoom.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/tokenTest")
    public String tokenTest(@AuthenticationPrincipal User user){
        return user.getUsername() + " OK";
    }

    @GetMapping("/tokenBlockTest")
    public String tokenBlockTest(@AuthenticationPrincipal User user){
        return user.getUsername() + " OK";
    }

    @GetMapping("/healthcheck")
    public String healthcheck(){
        return "OK";
    }
}

package com.timmy.TimmyRoom.auth;

import com.timmy.TimmyRoom.dto.LoginRequestDto;
import com.timmy.TimmyRoom.dto.SignupRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> getMemberProfile(@Valid @RequestBody LoginRequestDto request){
        String token = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto request){
        String token = authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }
}

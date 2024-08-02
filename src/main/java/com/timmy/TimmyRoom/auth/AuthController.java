package com.timmy.TimmyRoom.auth;

import com.timmy.TimmyRoom.dto.LoginRequestDto;
import com.timmy.TimmyRoom.dto.SignupRequestDto;
import com.timmy.TimmyRoom.dto.TokenDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> getMemberProfile(@Valid @RequestBody LoginRequestDto request){
        String accessToken = authService.login(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        return new ResponseEntity<>(new TokenDto(accessToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDto request){
        User user = authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(user.getEmail());
    }
}

package com.timmy.TimmyRoom.auth;

import com.timmy.TimmyRoom.dto.ErrorResponseDTO;
import com.timmy.TimmyRoom.dto.LoginRequestDTO;
import com.timmy.TimmyRoom.dto.SignupRequestDTO;
import com.timmy.TimmyRoom.dto.TokenDTO;
import com.timmy.TimmyRoom.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "사용자 인증")
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공", content = {@Content(schema = @Schema(implementation = TokenDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "사용자 없음", content = {@Content(schema = @Schema(implementation = ErrorResponseDTO.class))}),
            }
    )
    public ResponseEntity<TokenDTO> login(@Valid @RequestBody LoginRequestDTO request){
        String accessToken = authService.login(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        return new ResponseEntity<>(new TokenDTO(accessToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequestDTO request){
        User user = authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(user.getEmail());
    }
}

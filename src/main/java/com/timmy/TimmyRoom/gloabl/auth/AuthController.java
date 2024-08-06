package com.timmy.TimmyRoom.gloabl.auth;

import com.timmy.TimmyRoom.dto.request.LoginRequestDTO;
import com.timmy.TimmyRoom.dto.request.SignupRequestDTO;
import com.timmy.TimmyRoom.dto.response.TokenResponseDTO;
import com.timmy.TimmyRoom.entity.User;
import com.timmy.TimmyRoom.gloabl.error.ErrorResponse;
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
@RequestMapping("/api/v1/auth")
@Tag(name = "사용자 인증")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공", content = {@Content(schema = @Schema(implementation = TokenResponseDTO.class))}),
                    @ApiResponse(responseCode = "404", description = "사용자 없음", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            }
    )
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        String accessToken = authService.login(request);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + accessToken);
        return new ResponseEntity<>(new TokenResponseDTO(accessToken), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "회원가입 성공", content = {@Content(schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "409", description = "유저 아이디 중복", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            }
    )
    public ResponseEntity<User> signup(@Valid @RequestBody SignupRequestDTO request){
        User user = authService.signup(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

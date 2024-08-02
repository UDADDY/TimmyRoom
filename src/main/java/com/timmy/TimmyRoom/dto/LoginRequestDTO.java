package com.timmy.TimmyRoom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "로그인 DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @Schema(description = "이메일", example = "gustmd5715@gmail.com")
    @NotNull
    @Email
    private String email;

    @Schema(description = "비밀번호", example = "gggg")
    @NotNull
    private String password;
}

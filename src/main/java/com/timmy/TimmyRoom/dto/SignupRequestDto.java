package com.timmy.TimmyRoom.dto;

import lombok.Data;

@Data
public class SignupRequestDto {
    private final String email;
    private final String password;
    private final String name;
}

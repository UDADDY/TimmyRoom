package com.timmy.TimmyRoom.dto;

import com.timmy.TimmyRoom.auth.Member;
import lombok.Data;

@Data
public class SignupRequestDto {

    private final Long id;
    private final String email;
    private final String password;
    private final String name;
}

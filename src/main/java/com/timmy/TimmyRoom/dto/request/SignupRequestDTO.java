package com.timmy.TimmyRoom.dto.request;

import lombok.Data;

@Data
public class SignupRequestDTO {
    private String email;
    private String password;
    private String name;
}

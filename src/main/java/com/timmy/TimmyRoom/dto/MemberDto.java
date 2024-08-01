package com.timmy.TimmyRoom.dto;

import com.timmy.TimmyRoom.auth.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String role;
}

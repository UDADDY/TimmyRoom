package com.timmy.TimmyRoom.dto;

import com.timmy.TimmyRoom.auth.User;
import lombok.Getter;

@Getter
public class SessionUser {
    private String email;
    private String name;
    private String picture;

    public SessionUser(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
    }
}

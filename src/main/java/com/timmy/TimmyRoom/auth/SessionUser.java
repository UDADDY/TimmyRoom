package com.timmy.TimmyRoom.auth;

import com.timmy.TimmyRoom.entity.User;
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

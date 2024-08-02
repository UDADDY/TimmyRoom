package com.timmy.TimmyRoom.auth;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String email;
    private String password;
    private String name;

//    @Enumerated(EnumType.STRING)
    private String role;

    public User update(String name){
        this.name = name;

        return this;
    }
}

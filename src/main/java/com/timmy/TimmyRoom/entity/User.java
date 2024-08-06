package com.timmy.TimmyRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_email")
    @JsonIgnore
    private List<File> files;

    public User update(String name){
        this.name = name;

        return this;
    }
}

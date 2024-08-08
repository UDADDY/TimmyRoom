package com.timmy.TimmyRoom.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserMuseum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;

    @ManyToOne
    @JoinColumn(name = "museum_id")
    private Museum museum;

    @Builder
    public UserMuseum(User user, Museum museum) {
        this.user = user;
        this.museum = museum;
    }
}

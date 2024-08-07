package com.timmy.TimmyRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String host;

//    @JsonIgnore
//    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public ChatRoom(Long id, String name, String host) {
        this.id = id;
        this.name = name;
        this.host = host;
    }
}

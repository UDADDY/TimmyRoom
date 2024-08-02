package com.timmy.TimmyRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class File {
    @Id
    private String id;
    private String contentType;
    private Long size;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_email")
    @JsonIgnore
    private User user;

    @Builder
    public File(String id, String contentType, Long size, String name, User user) {
        this.id = id;
        this.contentType = contentType;
        this.size = size;
        this.name = name;
        this.user = user;
    }
}

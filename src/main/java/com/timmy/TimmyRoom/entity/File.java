package com.timmy.TimmyRoom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    @Builder
    public File(String id, String contentType, Long size, String name) {
        this.id = id;
        this.contentType = contentType;
        this.size = size;
        this.name = name;
    }
}

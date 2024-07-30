package com.timmy.TimmyRoom;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "test")
public class Test {
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

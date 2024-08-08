package com.timmy.TimmyRoom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
public class Museum {
    @Id
    private String name;

    private double latitude;
    private double longitude;
}

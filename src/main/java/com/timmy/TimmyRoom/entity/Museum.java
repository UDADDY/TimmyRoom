package com.timmy.TimmyRoom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Museum {
    @Id
    private Long id;

    private String name;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "museum", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<UserMuseum> userMuseums;
}

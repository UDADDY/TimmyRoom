package com.timmy.TimmyRoom.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(schema = "museum")
public class Museum {
    @Id
    @Column(name = "시설명")
    private String name;

    @Column(name = "박물관미술관구분")
    private String div;

    @Column(name = "소재지도로명주소")
    private String DoroAddress;

    @Column(name = "소재지지번주소")
    private String address;

    @Column(name = "위도")
    private double latitude;

    @Column(name = "경도")
    private double longitude;
}

package com.vanguard.weather.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "weatherinfo")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "main")
    private String main;
    @Column(name = "description")
    private String description;
    @Column(name = "icon")
    private String icon;

    public String getDescription() {
        return description;
    }

}

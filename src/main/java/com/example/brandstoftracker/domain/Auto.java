package com.example.brandstoftracker.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Auto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_id")
    private Long autoId;

    @Column(name = "plate_number")
    private String plateNumber;
    @Column(name = "merk")
    private String merk;
    @Column(name = "model")
    private String model;
}

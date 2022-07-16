package com.example.brandstoftracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "assignedAuto")
    @JsonIgnore
    private List<KmHistory> kmHistoryList;
}

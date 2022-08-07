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

    @Column(name = "tank_volume")
    private Long tankVolume;

    @Column(name = "available_brand_stof")
    private Long availableBrandStof;

    @Column(name = "year_of_construction")
    private Integer  yearOfConstruction;


    @OneToMany(mappedBy = "assignedAuto")
    @JsonIgnore
    private List<AutoUsage> autoUsageList;

    @OneToMany(mappedBy = "assignedAuto")
    @JsonIgnore
    private List<BrandStof> brandStofList;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ApplicationUser assignedUser;

}

package com.example.brandstoftracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class AutoUsage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auto_usage_id")
    private Long autoUsageId;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "brand_stof_verbruik")
    private Long brandStofVerbruik;

    @Column(name = "date")
    private LocalDate date;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto assignedAuto;
}

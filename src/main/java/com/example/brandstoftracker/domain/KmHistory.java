package com.example.brandstoftracker.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class KmHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "km_history_id")
    private Long kmHistoryId;

    @Column(name = "brand_stof")
    private BigDecimal brandStof;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "distance")
    private Integer distance;

    @Column(name = "brand_stof_verbruik")
    private BigDecimal brandStofVerbruik;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto assignedAuto;
}

package com.example.brandstoftracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class BrandStof {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_stof_id")
    private Long brandStofId;

    @Column(name = "brand_stof_amount")
    private Long brandStofAmount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "refueling_date")
    private LocalDateTime refuelingDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "auto_id")
    private Auto assignedAuto;
}

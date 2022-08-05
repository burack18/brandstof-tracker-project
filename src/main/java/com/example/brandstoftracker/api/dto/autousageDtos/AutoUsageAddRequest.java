package com.example.brandstoftracker.api.dto.autousageDtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoUsageAddRequest {
    private Integer distance;
    private Long brandStofVerbruik;
    private LocalDate date;
}

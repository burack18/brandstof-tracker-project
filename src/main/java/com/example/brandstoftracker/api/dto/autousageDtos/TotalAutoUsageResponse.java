package com.example.brandstoftracker.api.dto.autousageDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
public class TotalAutoUsageResponse {
    private Long totalDistance;
    private Long countOfUsage;
    private Long brandStofVerbruik;
    private String plateNumber;
}

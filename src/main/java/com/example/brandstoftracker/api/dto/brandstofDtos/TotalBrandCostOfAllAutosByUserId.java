package com.example.brandstoftracker.api.dto.brandstofDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalBrandCostOfAllAutosByUserId {
    private BigDecimal totalCost;
    private Long countOfRefueling;
}

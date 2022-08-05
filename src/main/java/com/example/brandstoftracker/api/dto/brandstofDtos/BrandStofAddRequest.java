package com.example.brandstoftracker.api.dto.brandstofDtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BrandStofAddRequest {
    private Long brandStofAmount;
    private BigDecimal price;
}

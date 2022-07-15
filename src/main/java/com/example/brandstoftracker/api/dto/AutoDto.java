package com.example.brandstoftracker.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AutoDto {
    private Long autoId;
    private String plateNumber;
    private String merk;
    private String model;

}

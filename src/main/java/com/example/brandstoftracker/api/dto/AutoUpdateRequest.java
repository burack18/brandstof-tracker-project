package com.example.brandstoftracker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoUpdateRequest {
    @NotBlank(message = "{validation.platenumber}")
    private String plateNumber;
    @NotBlank(message = "{validation.merk}")
    private String merk;
    @NotBlank(message = "{validation.model}")
    private String model;
    @NotNull(message = "{validation.model}")
    private Integer yearOfConstruction;
    @NotNull(message = "{validation.model}")
    private Long tankVolume;
}
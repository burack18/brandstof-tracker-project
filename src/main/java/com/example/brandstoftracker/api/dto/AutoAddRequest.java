package com.example.brandstoftracker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutoAddRequest {
    @NotBlank(message = "{validation.platenumber}")
    private String plateNumber;
    @NotBlank(message = "{validation.merk}")
    private String merk;
    @NotBlank(message = "{validation.model}")
    private String model;
    @NotNull
    private Integer yearOfConstruction;
    @NotNull
    private Long tankVolume;
}

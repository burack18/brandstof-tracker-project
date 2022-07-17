package com.example.brandstoftracker.utilities.mapper;

import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.domain.Auto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutoModelMapper {
    private final ModelMapper modelMapper;

    public AutoDto convertToDto(Auto auto) {
        AutoDto dto = modelMapper.map(auto, AutoDto.class);
        return dto;
    }


}

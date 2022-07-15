package com.example.brandstoftracker.service.abstracts;


import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.domain.Auto;

import java.util.List;

public interface AutoService {
    AutoDto getById(Long id);
    List<Auto> getAll();
}

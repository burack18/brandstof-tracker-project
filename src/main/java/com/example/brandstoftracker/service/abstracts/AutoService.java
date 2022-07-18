package com.example.brandstoftracker.service.abstracts;


import com.example.brandstoftracker.api.dto.AutoAddRequest;
import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.api.dto.AutoUpdateRequest;
import com.example.brandstoftracker.domain.Auto;

import java.util.List;

public interface AutoService {
    Auto getById(Long id);
    List<Auto> getAll();
    Auto add(AutoAddRequest auto);
    Auto update(Long id, AutoUpdateRequest auto);
    void delete(Long autoId);
}

package com.example.brandstoftracker.service.abstracts;


import com.example.brandstoftracker.api.dto.AutoAddRequest;
import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.api.dto.AutoUpdateRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.AutoUsageAddRequest;
import com.example.brandstoftracker.api.dto.brandstofDtos.BrandStofAddRequest;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.domain.BrandStof;

import java.util.List;

public interface AutoService {
    Auto getById(Long id);
    List<Auto> getAll();
    Auto add(AutoAddRequest auto);
    Auto update(Long id, AutoUpdateRequest auto);
    void delete(Long autoId);

    List<BrandStof> getBrandStofsByAutoId(Long autoid);

    BrandStof addBrandStofToAuto(Long autoid, BrandStofAddRequest brandStof);

    List<AutoUsage> getAutoUsagesByAutoId(Long autoid);

    AutoUsage addAutoUsageToAuto(Long autoid, AutoUsageAddRequest autoUsageAddRequest);
}

package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.domain.AutoUsage;

import java.util.List;

public interface AutoUsageService {
    List<AutoUsage> getAll();
    AutoUsage add(AutoUsage autoUsage);
    List<AutoUsage> findAllByAssignedAuto_AutoId(Long autoId);
}

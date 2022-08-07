package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.domain.AutoUsage;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AutoUsageService {
    List<AutoUsage> getAll();
    AutoUsage add(AutoUsage autoUsage);
    List<AutoUsage> findAllByAssignedAuto_AutoId(Long autoId);
    TotalAutoUsageResponse getTotalCostAllTime(@Param("autoId")Long autoId);

    TotalAutoUsageResponse getTotalAutoUsageCostByAutoIdAllTime(Long autoid, LocalDate date);
}

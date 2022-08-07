package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.domain.BrandStof;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BrandStofService {
    List<BrandStof> getAll();
    BrandStof add(BrandStof brandStof);
    BrandStof getById(Long brandStofId);
    List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId);
    TotalCostResponse getTotalCost(Long autoId, LocalDate date);

    TotalCostResponse getTotalCostAlltime(Long autoid);
}

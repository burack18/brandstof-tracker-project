package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.api.dto.brandstofDtos.BrandStofCostForMonth;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostForMonths;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.domain.BrandStof;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BrandStofService {
    List<BrandStof> getAll();
    BrandStof add(BrandStof brandStof);
    BrandStof getById(Long brandStofId);
    List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId);
    TotalCostResponse getTotalCost(Long autoId, LocalDate date);

    TotalCostResponse getTotalCostAlltime(Long autoid);
    TotalBrandCostOfAllAutosByUserId getTotalCostAlltimeAllautos();

    List<TotalCostForMonths> getTotalCostAlltimeAllautosForMonths();
    List<TotalCostForMonths> getTotalCostForEachMonthAfterDate(LocalDateTime date);

    TotalBrandCostOfAllAutosByUserId TotalBrandCostOfAllAutosByUserIdForMonths(LocalDateTime localDateTime);
}

package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.domain.BrandStof;

import java.util.List;

public interface BrandStofService {
    List<BrandStof> getAll();
    BrandStof add(BrandStof brandStof);
    BrandStof getById(Long brandStofId);
    List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId);

}

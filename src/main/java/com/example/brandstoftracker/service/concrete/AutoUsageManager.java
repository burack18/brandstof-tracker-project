package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.AutoUsageRepository;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.service.abstracts.AutoUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoUsageManager implements AutoUsageService {

    private final AutoUsageRepository repository;

    @Override
    public List<AutoUsage> getAll() {
        return repository.findAll();
    }

    @Override
    public AutoUsage add(AutoUsage autoUsage) {
        return this.repository.save(autoUsage);
    }

    @Override
    public List<AutoUsage> findAllByAssignedAuto_AutoId(Long autoId) {
        return this.repository.findAllByAssignedAuto_AutoId(autoId);
    }
}

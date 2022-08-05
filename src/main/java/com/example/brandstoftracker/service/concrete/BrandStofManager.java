package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.BrandStofRepository;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandStofManager implements BrandStofService {

    private final BrandStofRepository repository;

    @Override
    public List<BrandStof> getAll() {
        return repository.findAll();
    }

    @Override
    public BrandStof add(BrandStof brandStof) {
        return repository.save(brandStof);
    }

    @Override
    public BrandStof getById(Long brandStofId) {
        return repository.findById(brandStofId).orElseThrow(()->new NotFoundException("BrandStof not found"));
    }

    @Override
    public List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId) {
        return this.repository.findAllByAssignedAuto_AutoId(autoId);
    }
}

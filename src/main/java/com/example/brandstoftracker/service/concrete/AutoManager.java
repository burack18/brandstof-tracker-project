package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.utilities.mapper.AutoModelMapper;
import com.example.brandstoftracker.dao.AutoRepository;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoManager implements AutoService {
    private final AutoRepository repository;
    private final AutoModelMapper modelMapper;

    @Override
    public AutoDto getById(Long id) {
        Auto auto = repository.findById(id).orElseThrow(()->new NotFoundException("Auto not found"));
        return modelMapper.convertToDto(auto);
    }

    @Override
    public List<Auto> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Auto add(Auto auto) {
        return this.repository.save(auto);
    }

    @Override
    public Auto update(Auto auto) {
        return this.repository.save(auto);
    }

    @Override
    public void delete(Long autoId) {
        this.repository.deleteById(autoId);
    }
}

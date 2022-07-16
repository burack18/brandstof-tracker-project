package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.dao.AutoRepository;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.service.abstracts.AutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AutoManager implements AutoService {
    private final AutoRepository repository;

    @Override
    public AutoDto getById(Long id) {
        Auto auto = repository.findById(id).orElse(null);
        return new AutoDto();
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

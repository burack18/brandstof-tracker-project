package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.AutoRepository;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.service.abstracts.AutoService;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutoManager implements AutoService {
    private final AutoRepository repository;

    @Override
    public Auto getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}

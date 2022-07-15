package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.KmHistoryRepository;
import com.example.brandstoftracker.domain.KmHistory;
import com.example.brandstoftracker.service.abstracts.KmHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KmHistoryManager implements KmHistoryService {

    private final KmHistoryRepository repository;

    @Override
    public List<KmHistory> getAll() {
        return repository.findAll();
    }
}

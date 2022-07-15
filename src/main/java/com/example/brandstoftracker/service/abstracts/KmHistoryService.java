package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.domain.KmHistory;

import java.util.List;

public interface KmHistoryService {
    List<KmHistory> getAll();
}

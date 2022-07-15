package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.KmHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KmHistoryRepository extends JpaRepository<KmHistory ,Long> {
}

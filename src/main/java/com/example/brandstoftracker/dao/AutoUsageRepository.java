package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.AutoUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoUsageRepository extends JpaRepository<AutoUsage,Long> {
    List<AutoUsage> findAllByAssignedAuto_AutoId(Long autoId);
}

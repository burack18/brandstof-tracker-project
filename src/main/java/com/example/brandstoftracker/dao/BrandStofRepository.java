package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.BrandStof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandStofRepository extends JpaRepository<BrandStof,Long> {
    List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId);
}

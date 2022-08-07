package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long> {
    List<Auto> findAllByAssignedUser_UserId(Long userId);
}

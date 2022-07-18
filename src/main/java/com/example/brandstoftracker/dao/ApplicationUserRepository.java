package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long> {
    Optional<ApplicationUser> findByUserName(String username);
}
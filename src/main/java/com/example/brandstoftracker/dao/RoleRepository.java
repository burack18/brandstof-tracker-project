package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}

package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.domain.Role;

public interface RoleService {
    Role getRoleById(Long id);
    Role getByName(String name);
}

package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.RoleRepository;
import com.example.brandstoftracker.domain.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleManagerTest {

    @Mock
    private RoleRepository repository;

    private RoleManager underTest;

    private Role role;
    private Optional<Role> optionalRole;

    @BeforeEach
    void setUp() {
        role=new Role();
        role.setId(1L);
        role.setName("admin");
        optionalRole=Optional.of(role);
        underTest=new RoleManager(repository);
    }

    @Test
    void getRoleById() {
        when(repository.findById(1L)).thenReturn(optionalRole);
        Role testRoleById = underTest.getRoleById(1L);
        assertEquals(1L,testRoleById.getId());
        assertEquals("admin",testRoleById.getName());
    }

    @Test
    void getByName() {
        when(repository.findByName("admin")).thenReturn(optionalRole);
        Role testRoleById = underTest.getByName("admin");
        assertEquals(1L,testRoleById.getId());
        assertEquals("admin",testRoleById.getName());
    }
}
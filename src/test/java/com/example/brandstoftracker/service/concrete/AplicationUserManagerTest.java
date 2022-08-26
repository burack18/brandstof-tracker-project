package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.ApplicationUserRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AplicationUserManagerTest {

    private AplicationUserManager underTest;
    @Mock
    private ApplicationUserRepository repository;

    private Optional<ApplicationUser> userOptional;
    private ApplicationUser user;
    @BeforeEach
    void setUp() {
        user=new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin@mail.com");
        user.setFirstName("admin");
        userOptional=Optional.of(user);
    underTest=new AplicationUserManager(repository);
    }
    @Test
    void add() {
        when(repository.save(any(ApplicationUser.class))).thenReturn(user);
        ApplicationUser savedUser = underTest.add(user);
        assertEquals(1L,savedUser.getUserId());
        assertEquals("admin@mail.com",savedUser.getEmail());
        assertEquals("admin",savedUser.getFirstName());
    }

    @Test
    void findByEmail() {
        when(repository.findByEmail("admin@mail.com")).thenReturn(userOptional);
        ApplicationUser test = underTest.findByEmail("admin@mail.com");
        assertEquals(1L,test.getUserId());
        assertEquals("admin@mail.com",test.getEmail());
        assertEquals("admin",test.getFirstName());
    }
}
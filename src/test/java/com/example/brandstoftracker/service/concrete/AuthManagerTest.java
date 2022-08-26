package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.LoginRequest;
import com.example.brandstoftracker.api.dto.LoginResponse;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.security.ApplicationUserPrincipal;
import com.example.brandstoftracker.security.JWTTokenProvider;
import com.example.brandstoftracker.security.UserPrincipalManager;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.RoleService;
import com.sun.security.auth.UserPrincipal;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthManagerTest {

    @Mock
    private  AuthenticationManager manager;
    @Mock
    private  JWTTokenProvider provider;
    @Mock
    private  UserPrincipalManager userPrincipalManager;
    @Mock
    private  PasswordEncoder encoder;
    @Mock
    private  ApplicationUserService applicationUserService;
    @Mock
    private  RoleService roleService;

    private AuthManager underTest;

    @BeforeEach
    void setUp() {
        underTest=new AuthManager(manager,
                provider,
                userPrincipalManager,
                encoder,
                applicationUserService,
                roleService);

    }

    @Test
    void login() {
        LoginRequest loginRequest=new LoginRequest();
        loginRequest.setEmail("admin@hotmail.com");
        loginRequest.setPassword("password");
        ApplicationUser user=new ApplicationUser();
        user.setEmail("admin@hotmail.com");
        ApplicationUserPrincipal userPrincipal=new ApplicationUserPrincipal(user);
        when(userPrincipalManager.loadUserByUsername("admin@hotmail.com")).thenReturn(userPrincipal);
        LoginResponse testResponse = underTest.login(loginRequest);
        assertEquals(testResponse.getUserName(),"admin@hotmail.com");
    }

    @Test
    void register() {
        assertDoesNotThrow(() -> new RuntimeException());
    }
}
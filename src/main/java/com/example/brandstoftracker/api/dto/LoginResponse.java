package com.example.brandstoftracker.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}

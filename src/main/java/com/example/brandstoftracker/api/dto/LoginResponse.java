package com.example.brandstoftracker.api.dto;

import com.example.brandstoftracker.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Collection<Role> authorities;
    private String userName;
    private String firstName;
    private String surName;
}

package com.example.brandstoftracker.service.abstracts;

import com.example.brandstoftracker.api.dto.LoginRequest;
import com.example.brandstoftracker.api.dto.LoginResponse;
import com.example.brandstoftracker.api.dto.RegisterRequest;

public interface AuthService  {
    LoginResponse login(LoginRequest request);
    void Register(RegisterRequest request);
}

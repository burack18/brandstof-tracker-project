package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.LoginRequest;
import com.example.brandstoftracker.api.dto.LoginResponse;
import com.example.brandstoftracker.api.dto.RegisterRequest;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.Role;
import com.example.brandstoftracker.security.ApplicationUserPrincipal;
import com.example.brandstoftracker.security.JWTTokenProvider;
import com.example.brandstoftracker.security.UserPrincipalManager;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.AuthService;
import com.example.brandstoftracker.service.abstracts.RoleService;
import com.sun.security.auth.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {

    private final AuthenticationManager manager;
    private final JWTTokenProvider provider;
    private final UserPrincipalManager userPrincipalManager;
    private final PasswordEncoder encoder;
    private final ApplicationUserService applicationUserService;
    private final RoleService roleService;

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
        Authentication authenticate = manager.authenticate(token);
        ApplicationUserPrincipal userDetails = (ApplicationUserPrincipal) userPrincipalManager.loadUserByUsername(request.getEmail());
        ApplicationUser applicationUser=userDetails.getApplicationUser();
        String generateJwtToken = provider.generateJwtToken(userDetails);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        return new LoginResponse("Bearer "+generateJwtToken, applicationUser.getRoles(),applicationUser.getEmail(),applicationUser.getFirstName(),applicationUser.getSurName());
    }

    @Override
    public void Register(RegisterRequest request) {
        //Default role ADMIN
        Role role=roleService.getByName("admin");
        ApplicationUser applicationUser=new ApplicationUser();
        applicationUser.setFirstName(request.getFirstName());
        applicationUser.setEmail(request.getEmail());
        applicationUser.setSurName(request.getLastName());
        applicationUser.setPassword(encoder.encode(request.getPassword()));
        applicationUser.getRoles().add(role);
        applicationUserService.add(applicationUser);
    }
}

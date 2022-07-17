package com.example.brandstoftracker.security;

import com.example.brandstoftracker.domain.ApplicationUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class ApplicationUserPrincipal implements UserDetails {
    private final ApplicationUser applicationUser;

    public ApplicationUserPrincipal(ApplicationUser applicationUser){

        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.applicationUser.getRoles().stream().map(x->new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.applicationUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

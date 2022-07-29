package com.example.brandstoftracker.security;


import com.example.brandstoftracker.dao.ApplicationUserRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPrincipalManager implements UserDetailsService {

    private final ApplicationUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ApplicationUser> user=repository.findByEmail(email);

        ApplicationUserPrincipal userPrincipal=new ApplicationUserPrincipal(user.orElseThrow(()->new UsernameNotFoundException("notfound")));
        return userPrincipal;
    }
}
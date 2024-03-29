package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.ApplicationUserRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AplicationUserManager  implements ApplicationUserService {
    private final ApplicationUserRepository repository;


    @Override
    public ApplicationUser add(ApplicationUser user) {
        return repository.save(user);
    }

    @Override
    public ApplicationUser findByEmail(String email) {
        return this.repository.findByEmail(email).orElseThrow(()->new NotFoundException("User not Found"));
    }
}

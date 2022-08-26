package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.dao.RoleRepository;
import com.example.brandstoftracker.domain.Role;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleManager implements RoleService {
    private final RoleRepository repository;
    @PostConstruct
    public void addDefaultRole(){
        Optional<Role> role = repository.findByName("admin");
        if(!role.isPresent()){
            Role defaultAdminRole=new Role();
            defaultAdminRole.setName("admin");
            repository.save(defaultAdminRole);
        }
    }

    @Override
    public Role getRoleById(Long id) {
        return repository.findById(id).orElseThrow(()->new NotFoundException("Not Found"));
    }

    @Override
    public Role getByName(String name) {
        return repository.findByName(name).orElseThrow(()->new NotFoundException("Not Found"));
    }
}

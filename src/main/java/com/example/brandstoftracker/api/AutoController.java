package com.example.brandstoftracker.api;

import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.service.abstracts.AutoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/autos")
@AllArgsConstructor
public class AutoController {
    private final AutoService service;

    @GetMapping("/{id}")
    public Auto getById(@PathVariable Long id){
        return this.service.getById(id);
    }
}

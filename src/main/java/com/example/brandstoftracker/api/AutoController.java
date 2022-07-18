package com.example.brandstoftracker.api;

import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.api.httpResponse.DataResponse;
import com.example.brandstoftracker.api.httpResponse.SuccessDataResponse;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.service.abstracts.AutoService;
import com.example.brandstoftracker.utilities.languageLocalization.MessageCreater;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/autos")
@AllArgsConstructor
public class AutoController {
    private final AutoService service;
    private final MessageCreater messageCreater;

    @GetMapping
    public ResponseEntity getAll(){
        List<Auto> all = this.service.getAll();
        return  new ResponseEntity(new SuccessDataResponse(messageCreater.getMessage("autoslisted"), all), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public AutoDto getById(@PathVariable Long id){
        return this.service.getById(id);
    }
}

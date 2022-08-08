package com.example.brandstoftracker.api;

import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.service.abstracts.AutoUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auto-usages")
@RequiredArgsConstructor
public class AutoUsageController {

    private final AutoUsageService service;

    @GetMapping
    public ResponseEntity getTotalKmOfAllAutosByUserId( @RequestParam(name = "date",required = false)@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate date){
        TotalAutoUsageResponse allTime;
        if(date!=null){
         allTime= this.service.getTotalAutoUsageCostAllTime(date);
        }else {
            allTime=this.service.getTotalAutoUsageCostAllTime();
        }
        return ResponseEntity.ok(allTime);
    }
}

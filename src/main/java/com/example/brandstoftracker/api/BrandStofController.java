package com.example.brandstoftracker.api;

import com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostForMonths;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.api.httpResponse.SuccessDataResponse;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brandstofs")
public class BrandStofController {

    private final BrandStofService service;

    @GetMapping
    public ResponseEntity getTotalCostByUserId(){
        TotalBrandCostOfAllAutosByUserId totalCostAlltimeAllautos = this.service.getTotalCostAlltimeAllautos();
        return new ResponseEntity(new SuccessDataResponse("BrandStofs listed", totalCostAlltimeAllautos), HttpStatus.OK);
    }
    @GetMapping("/months")
    public ResponseEntity getTotalCostByUserIdForMonths(@RequestParam(name = "date",required = false)@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate date){
        List<TotalCostForMonths> data;
        if(date!=null)
        {
            LocalDateTime localDateTime = date.atStartOfDay();
            data = this.service.getTotalCostForEachMonthAfterDate(localDateTime);
        }
        else{
            data=this.service.getTotalCostAlltimeAllautosForMonths();
        }
        return new ResponseEntity(new SuccessDataResponse("BrandStofs listed",data), HttpStatus.OK);
    }
    @GetMapping("/totalcost")
    public ResponseEntity getTotalCostByUserId(@RequestParam(name = "date",required = false)@DateTimeFormat(pattern="MM/dd/yyyy") LocalDate date){
        TotalBrandCostOfAllAutosByUserId data;
        if(date!=null)
        {
            LocalDateTime localDateTime = date.atStartOfDay();
            data = this.service.TotalBrandCostOfAllAutosByUserIdForMonths(localDateTime);
        }
        else{
            data=this.service.getTotalCostAlltimeAllautos();
        }
        return new ResponseEntity(new SuccessDataResponse("BrandStofs listed",data), HttpStatus.OK);
    }

}

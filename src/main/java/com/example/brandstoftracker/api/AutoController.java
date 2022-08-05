package com.example.brandstoftracker.api;

import com.example.brandstoftracker.api.dto.AutoAddRequest;
import com.example.brandstoftracker.api.dto.AutoUpdateRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.AutoUsageAddRequest;
import com.example.brandstoftracker.api.dto.brandstofDtos.BrandStofAddRequest;
import com.example.brandstoftracker.api.httpResponse.DataResponse;
import com.example.brandstoftracker.api.httpResponse.Response;
import com.example.brandstoftracker.api.httpResponse.SuccessDataResponse;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.AutoService;
import com.example.brandstoftracker.utilities.languageLocalization.MessageCreater;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return  ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), all));
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Auto auto = this.service.getById(id);
        return ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), auto));
    }

    @PostMapping
    public ResponseEntity add(@RequestBody @Valid AutoAddRequest auto){
        return new ResponseEntity(new SuccessDataResponse(messageCreater.getMessage("auto.created"), this.service.add(auto)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.service.delete(id);
        return new ResponseEntity(new Response(messageCreater.getMessage("auto.deleted")),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id,@RequestBody @Valid AutoUpdateRequest request){
        return new ResponseEntity(new SuccessDataResponse(messageCreater.getMessage("auto.update"),this.service.update(id,request)),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{autoid}/brandstofs")
    public ResponseEntity getBrandStofsByAutoId(@PathVariable Long autoid){
        List<BrandStof> brandStofList = this.service.getBrandStofsByAutoId(autoid);
        return ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), brandStofList));
    }
    @PostMapping("/{autoid}/brandstofs")
    public ResponseEntity addBrandStoftofToAuto(@PathVariable Long autoid ,@RequestBody BrandStofAddRequest brandStof){
        BrandStof addedBrandStof = this.service.addBrandStofToAuto(autoid,brandStof);
        return ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), addedBrandStof));
    }
    @GetMapping("/{autoid}/auto-usages")
    public ResponseEntity getAutoUsagesByAutoId(@PathVariable Long autoid){
        List<AutoUsage> autoUsages = this.service.getAutoUsagesByAutoId(autoid);
        return ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), autoUsages));
    }

    @PostMapping("/{autoid}/auto-usages")
    public ResponseEntity addAutoUsageToAuto(@PathVariable Long autoid,@RequestBody AutoUsageAddRequest autoUsageAddRequest){
        AutoUsage addedAutoUsage = this.service.addAutoUsageToAuto(autoid,autoUsageAddRequest);
        return ResponseEntity.ok(new SuccessDataResponse(messageCreater.getMessage("auto.listed"), addedAutoUsage));
    }
}

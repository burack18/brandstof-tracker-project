package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.AutoAddRequest;
import com.example.brandstoftracker.api.dto.AutoDto;
import com.example.brandstoftracker.api.dto.AutoUpdateRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.AutoUsageAddRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.api.dto.brandstofDtos.BrandStofAddRequest;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.exceptionHandler.exceptions.InsufficientException;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.AutoUsageService;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import com.example.brandstoftracker.utilities.mapper.AutoModelMapper;
import com.example.brandstoftracker.dao.AutoRepository;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.AutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.persistence.Tuple;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoManager implements AutoService {
    private final AutoRepository repository;
    private final AutoModelMapper modelMapper;
    private final BrandStofService brandStofManager;
    private final AutoUsageService autoUsageService;
    private final ApplicationUserService userService;

    @Override
    public Auto getById(Long id) {
        Auto auto = repository.findById(id).orElseThrow(()->new NotFoundException("Auto not found"));
        return auto;
    }

    @Override
    public List<Auto> getAll() {
        ApplicationUser user = getContextUser();
        List<Auto> allAutosAssignedToUser = this.repository.findAllByAssignedUser_UserId(user.getUserId());
        return allAutosAssignedToUser;
    }

    @Override
    public Auto add(AutoAddRequest auto) {
        Auto savedAuto = modelMapper.convertToAuto(auto);
        ApplicationUser user = getContextUser();
        savedAuto.setAssignedUser(user);
        return this.repository.save(savedAuto);
    }

    @Override
    public Auto update(Long id, AutoUpdateRequest auto) {
        Auto modifiedAuto = this.getById(id);
        modifiedAuto.setModel(auto.getModel());
        modifiedAuto.setMerk(auto.getMerk());
        modifiedAuto.setPlateNumber(auto.getPlateNumber());
        modifiedAuto.setTankVolume(auto.getTankVolume());
        modifiedAuto.setYearOfConstruction(auto.getYearOfConstruction());
        return this.repository.save(modifiedAuto);
    }

    @Override
    public void delete(Long autoId) {
        this.repository.deleteById(autoId);
    }

    @Override
    public List<BrandStof> getBrandStofsByAutoId(Long autoid) {
        this.getById(autoid);
        return this.brandStofManager.findAllByAssignedAuto_AutoId(autoid);
    }

    @Transactional
    @Override
    public BrandStof addBrandStofToAuto(Long autoid, BrandStofAddRequest brandStof) {
        Auto auto=getById(autoid);
        Long availableBrandStof=auto.getAvailableBrandStof();
        if((availableBrandStof!=null&&availableBrandStof+brandStof.getBrandStofAmount()>auto.getTankVolume())||brandStof.getBrandStofAmount()>auto.getTankVolume()){
            throw new InsufficientException("Tank volume is insufficient");
        }
        if(availableBrandStof!=null)auto.setAvailableBrandStof(brandStof.getBrandStofAmount()+availableBrandStof);
        else auto.setAvailableBrandStof(brandStof.getBrandStofAmount());
        Auto modifiedAuto = repository.save(auto);
        BrandStof addedBrandStof=new BrandStof();
        addedBrandStof.setBrandStofAmount(brandStof.getBrandStofAmount());
        addedBrandStof.setPrice(brandStof.getPrice());
        addedBrandStof.setAssignedAuto(auto);
        addedBrandStof.setRefuelingDate(LocalDateTime.now());
        return this.brandStofManager.add(addedBrandStof);
    }

    @Override
    public List<AutoUsage> getAutoUsagesByAutoId(Long autoid) {
        return this.autoUsageService.findAllByAssignedAuto_AutoId(autoid);
    }

    @Override
    public AutoUsage addAutoUsageToAuto(Long autoid, AutoUsageAddRequest autoUsageAddRequest) {
        Auto auto=this.getById(autoid);
        Long availableStof=auto.getAvailableBrandStof();
        if(availableStof==null||availableStof-autoUsageAddRequest.getBrandStofVerbruik()<0)throw new InsufficientException("Insufficient brandstof");
        auto.setAvailableBrandStof(availableStof-autoUsageAddRequest.getBrandStofVerbruik());
        Auto modifiedAuto = this.repository.save(auto);
        AutoUsage autoUsage=new AutoUsage();
        autoUsage.setAssignedAuto(auto);
        autoUsage.setDate(autoUsageAddRequest.getDate());
        autoUsage.setBrandStofVerbruik(autoUsageAddRequest.getBrandStofVerbruik());
        autoUsage.setDistance(autoUsageAddRequest.getDistance());
        autoUsage.setAssignedAuto(auto);
        return this.autoUsageService.add(autoUsage);
    }

    @Override
    public TotalCostResponse getTotalBrandstofCostByAutoId(Long autoid, LocalDate date) {
        return this.brandStofManager.getTotalCost(autoid,date);
    }

    @Override
    public TotalCostResponse getTotalBrandstofCostByAutoIdAllTime(Long autoid) {
        return this.brandStofManager.getTotalCostAlltime(autoid);
    }

    @Override
    public TotalAutoUsageResponse getTotalCostAllTime(Long autoId) {
        return this.autoUsageService.getTotalCostAllTime(autoId);
    }

    @Override
    public TotalAutoUsageResponse getTotalAutoUsageCostByAutoIdAllTime(Long autoid, LocalDate date) {
        return this.autoUsageService.getTotalAutoUsageCostByAutoIdAllTime(autoid,date);
    }

    public ApplicationUser getContextUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser user = userService.findByEmail(authentication.getName());
        return user;
    }
}

package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.dao.BrandStofRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.exceptionHandler.exceptions.NotFoundException;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BrandStofManager implements BrandStofService {

    private final BrandStofRepository repository;
    private final ApplicationUserService userService;

    @Override
    public List<BrandStof> getAll() {
        return repository.findAll();
    }

    @Override
    public BrandStof add(BrandStof brandStof) {
        return repository.save(brandStof);
    }

    @Override
    public BrandStof getById(Long brandStofId) {
        return repository.findById(brandStofId).orElseThrow(()->new NotFoundException("BrandStof not found"));
    }

    @Override
    public List<BrandStof> findAllByAssignedAuto_AutoId(Long autoId) {
        return this.repository.findAllByAssignedAuto_AutoIdOrderByRefuelingDateDesc(autoId);
    }

    @Override
    public TotalCostResponse getTotalCost(Long autoId, LocalDate date) {
        LocalDateTime time = date.atStartOfDay();
        ApplicationUser user = getContextUser();
        TotalCostResponse totalCost = this.repository.getTotalCost(autoId,user.getUserId(),time);
        return totalCost;
    }

    @Override
    public TotalCostResponse getTotalCostAlltime(Long autoid) {
        ApplicationUser user = getContextUser();
        return this.repository.getTotalCostAllTime(autoid,user.getUserId());
    }


    public ApplicationUser getContextUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser user = userService.findByEmail(authentication.getName());
        return user;
    }
}

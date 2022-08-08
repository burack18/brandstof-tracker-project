package com.example.brandstoftracker.service.concrete;


import com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostForMonths;
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

    @Override
    public TotalBrandCostOfAllAutosByUserId getTotalCostAlltimeAllautos() {
        ApplicationUser user = getContextUser();
        return this.repository.getTotalCostAlltimeAllautos(user.getUserId());
    }

    @Override
    public List<TotalCostForMonths> getTotalCostAlltimeAllautosForMonths() {
        ApplicationUser user = getContextUser();
        List<TotalCostForMonths> totalCostForEachMonth = this.repository.getTotalCostForEachMonth(user.getUserId());
        return totalCostForEachMonth;
    }

    @Override
    public List<TotalCostForMonths> getTotalCostForEachMonthAfterDate(LocalDateTime date) {
        ApplicationUser user = getContextUser();
        List<TotalCostForMonths> eachMonthAfterDate = this.repository.getTotalCostForEachMonthAfterDate(date, user.getUserId());
        return eachMonthAfterDate;
    }

    @Override
    public TotalBrandCostOfAllAutosByUserId TotalBrandCostOfAllAutosByUserIdForMonths(LocalDateTime localDateTime) {
        ApplicationUser user = getContextUser();
        TotalBrandCostOfAllAutosByUserId totalCostAlltimeAllautosForMonths = this.repository.getTotalCostAlltimeAllautosForMonths(localDateTime, user.getUserId());
        return totalCostAlltimeAllautosForMonths;
    }


    public ApplicationUser getContextUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser user = userService.findByEmail(authentication.getName());
        return user;
    }
}

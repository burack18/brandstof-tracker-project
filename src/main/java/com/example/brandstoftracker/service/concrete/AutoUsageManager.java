package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.dao.AutoUsageRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.AutoUsageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AutoUsageManager implements AutoUsageService {

    private final AutoUsageRepository repository;
    private final ApplicationUserService userService;

    @Override
    public List<AutoUsage> getAll() {
        return repository.findAll();
    }

    @Override
    public AutoUsage add(AutoUsage autoUsage) {
        return this.repository.save(autoUsage);
    }

    @Override
    public List<AutoUsage> findAllByAssignedAuto_AutoId(Long autoId) {
        return this.repository.findAllByAssignedAuto_AutoIdOrderByDateDesc(autoId);
    }

    @Override
    public TotalAutoUsageResponse getTotalCostAllTime(Long autoId) {
        ApplicationUser user = getContextUser();
        return this.repository.getTotalCostAllTime(autoId,user.getUserId());
    }

    @Override
    public TotalAutoUsageResponse getTotalAutoUsageCostByAutoIdAllTime(Long autoid, LocalDate date) {
        ApplicationUser user = getContextUser();
        return this.repository.getTotalAutoUsageCostByAutoIdAllTime(autoid,user.getUserId(),date);
    }

    public ApplicationUser getContextUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ApplicationUser user = userService.findByEmail(authentication.getName());
        return user;
    }
}

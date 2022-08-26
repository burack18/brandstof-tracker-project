package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.dao.AutoUsageRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.lang.reflect.Method;
import java.net.PasswordAuthentication;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AutoUsageManagerTest {

    @Mock
    private  AutoUsageRepository repository;
    @Mock
    private  ApplicationUserService userService;

    private AutoUsageManager underTest;

    private AutoUsage autoUsage;

    @BeforeEach
    void setUp() {
        autoUsage=new AutoUsage();
        autoUsage.setAutoUsageId(1L);
        autoUsage.setDistance(100);
        autoUsage.setDate(LocalDate.of(2022,01,01));
        autoUsage.setBrandStofVerbruik(50L);
        underTest=new AutoUsageManager(repository,userService);


    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(autoUsage));
        List<AutoUsage> testAll = underTest.getAll();

        assertEquals(1,testAll.size());
    }

    @Test
    void add() {
        when(repository.save(any(AutoUsage.class))).thenReturn(autoUsage);
        AutoUsage savedUsage = underTest.add(autoUsage);
        assertEquals(savedUsage,autoUsage);
    }

    @Test
    void findAllByAssignedAuto_AutoId() {
        when(repository.findAllByAssignedAuto_AutoIdOrderByDateDesc(1L)).thenReturn(List.of(autoUsage));
        List<AutoUsage> test = underTest.findAllByAssignedAuto_AutoId(1L);
        assertEquals(1,test.size());
    }

    @Test
    void getTotalCostAllTime() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        TotalAutoUsageResponse response = new TotalAutoUsageResponse();
        response.setCountOfUsage(10L);
        response.setTotalDistance(100L);
        when(repository.getTotalCostAllTime(1L,1L)).thenReturn(response);
        TotalAutoUsageResponse testTotalCostAllTime = underTest.getTotalCostAllTime(1L);
        assertEquals(100L,testTotalCostAllTime.getTotalDistance());
        assertEquals(10L,testTotalCostAllTime.getCountOfUsage());
    }

    @Test
    void getTotalAutoUsageCostByAutoIdAllTime() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        TotalAutoUsageResponse totalAutoUsageResponse=new TotalAutoUsageResponse();
        totalAutoUsageResponse.setTotalDistance(100L);
        totalAutoUsageResponse.setCountOfUsage(5L);
        when(repository.getTotalAutoUsageCostByAutoIdAllTime(1L,1l,LocalDate.of(2022, 1, 1))).thenReturn(totalAutoUsageResponse);
        TotalAutoUsageResponse test = underTest.getTotalAutoUsageCostByAutoIdAllTime(1L, LocalDate.of(2022, 1, 1));

        assertEquals(100L,test.getTotalDistance());
        assertEquals(5L,test.getCountOfUsage());
    }

}
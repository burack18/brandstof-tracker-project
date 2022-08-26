package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.AutoAddRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.AutoUsageAddRequest;
import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.api.dto.brandstofDtos.BrandStofAddRequest;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.dao.AutoRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.Auto;
import com.example.brandstoftracker.domain.AutoUsage;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.AutoService;
import com.example.brandstoftracker.service.abstracts.AutoUsageService;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import com.example.brandstoftracker.utilities.mapper.AutoModelMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.lang.model.element.UnknownDirectiveException;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AutoManagerTest {
    @Mock
    private  AutoRepository repository;
    @Mock
    private  AutoModelMapper modelMapper;
    @Mock
    private  BrandStofService brandStofManager;
    @Mock
    private  AutoUsageService autoUsageService;
    @Mock
    private  ApplicationUserService userService;

    private AutoService underTest;

    private Auto auto;

    private Optional<Auto> optionalAuto;
    @BeforeEach
    void setUp() {
        auto=new Auto();
        auto.setAutoId(1L);
        auto.setMerk("merk");
        auto.setAvailableBrandStof(200L);
        optionalAuto=Optional.of(auto);
        underTest=new AutoManager(repository,
                modelMapper,
                brandStofManager,
                autoUsageService,
                userService);
    }

    @Test
    void getById() {
        when(repository.findById(1L)).thenReturn(optionalAuto);
        Auto test = underTest.getById(1L);
        assertEquals(1L,test.getAutoId());
        assertEquals("merk",test.getMerk());
    }

    @Test
    void getAll() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        auto.setAssignedUser(user);
        when(repository.findAllByAssignedUser_UserId(1L)).thenReturn(List.of(auto));
        List<Auto> testAll = underTest.getAll();
        assertEquals(1,testAll.size());
    }

    @Test
    void add() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        when(modelMapper.convertToAuto(any(AutoAddRequest.class))).thenReturn(auto);
        when(repository.save(auto)).thenReturn(auto);
        AutoAddRequest addRequest=new AutoAddRequest();
        Auto test = underTest.add(addRequest);
        assertEquals(1L,test.getAutoId());
    }

    @Test
    void delete() {
        underTest.delete(1l);
        assertDoesNotThrow(()->new RuntimeException());
        verify(repository,atLeastOnce()).deleteById(1L);
    }

    @Test
    void getBrandStofsByAutoId() {

        when(repository.findById(1l)).thenReturn(optionalAuto);
        BrandStof brandStof=new BrandStof();
        when(brandStofManager.findAllByAssignedAuto_AutoId(1L)).thenReturn(List.of(brandStof));
        List<BrandStof> testBrandStofsByAutoId = underTest.getBrandStofsByAutoId(1L);

        verify(brandStofManager,atLeastOnce()).findAllByAssignedAuto_AutoId(1L);
        assertEquals(1,testBrandStofsByAutoId.size());
    }

    @Test
    void addBrandStofToAuto() {
        BrandStof brandStof=new BrandStof();
        brandStof.setBrandStofAmount(100L);
        auto.setTankVolume(250l);
        auto.setAvailableBrandStof(50L);
        Optional<Auto> optional = Optional.of(this.auto);
        when(repository.findById(1L)).thenReturn(optional);
        when(brandStofManager.add(any(BrandStof.class))).thenReturn(brandStof);
        BrandStofAddRequest request=new BrandStofAddRequest();
        request.setBrandStofAmount(100L);

        BrandStof test = underTest.addBrandStofToAuto(1L,request);

        verify(brandStofManager,atLeastOnce()).add(any(BrandStof.class));
        assertEquals(100l,test.getBrandStofAmount());
    }

    @Test
    void getAutoUsagesByAutoId() {
        AutoUsage autoUsage=new AutoUsage();
        autoUsage.setAutoUsageId(1L);
        when(autoUsageService.findAllByAssignedAuto_AutoId(1L)).thenReturn(List.of(autoUsage));
        List<AutoUsage> test = underTest.getAutoUsagesByAutoId(1L);

        verify(autoUsageService,atLeastOnce()).findAllByAssignedAuto_AutoId(1L);
        assertEquals(1,test.size());
    }

    @Test
    void addAutoUsageToAuto() {
        when(repository.findById(1L)).thenReturn(optionalAuto);
        when(repository.save(any(Auto.class))).thenReturn(auto);
        AutoUsage autoUsage=new AutoUsage();
        autoUsage.setAutoUsageId(1L);
        AutoUsageAddRequest autoUsageAddRequest=new AutoUsageAddRequest();
        autoUsageAddRequest.setBrandStofVerbruik(5L);
        when(autoUsageService.add(any(AutoUsage.class))).thenReturn(autoUsage);
        AutoUsage test = underTest.addAutoUsageToAuto(1L, autoUsageAddRequest);

        verify(autoUsageService,atLeastOnce()).add(any(AutoUsage.class));
        assertEquals(1L,test.getAutoUsageId());
    }

    @Test
    void getTotalBrandstofCostByAutoId() {
        TotalCostResponse response=new TotalCostResponse();
        response.setTotalCost(BigDecimal.valueOf(100));
        LocalDate date=LocalDate.of(2022,1,1);
        when(brandStofManager.getTotalCost(1L,date)).thenReturn(response);
        TotalCostResponse test = underTest.getTotalBrandstofCostByAutoId(1L, date);

        verify(brandStofManager,atLeastOnce()).getTotalCost(1L,date);
        assertEquals(BigDecimal.valueOf(100),test.getTotalCost());

    }

    @Test
    void getTotalBrandstofCostByAutoIdAllTime() {
        TotalCostResponse response=new TotalCostResponse();
        response.setTotalCost(BigDecimal.valueOf(100));
        when(brandStofManager.getTotalCostAlltime(1L)).thenReturn(response);
        TotalCostResponse test = underTest.getTotalBrandstofCostByAutoIdAllTime(1L);

        verify(brandStofManager,atLeastOnce()).getTotalCostAlltime(1L);
        assertEquals(BigDecimal.valueOf(100),test.getTotalCost());
    }



    @Test
    void getTotalAutoUsageCostByAutoIdAllTime() {
        LocalDate date=LocalDate.of(2022,1,1);
        TotalAutoUsageResponse response=new TotalAutoUsageResponse();
        response.setTotalDistance(100L);
        when(autoUsageService.getTotalAutoUsageCostByAutoIdAllTime(1L,date)).thenReturn(response);
        TotalAutoUsageResponse test = underTest.getTotalAutoUsageCostByAutoIdAllTime(1L, date);

        verify(autoUsageService,atLeastOnce()).getTotalAutoUsageCostByAutoIdAllTime(1L,date);
        assertEquals(100,test.getTotalDistance());

    }

}
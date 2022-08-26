package com.example.brandstoftracker.service.concrete;

import com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostForMonths;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.dao.BrandStofRepository;
import com.example.brandstoftracker.domain.ApplicationUser;
import com.example.brandstoftracker.domain.BrandStof;
import com.example.brandstoftracker.service.abstracts.ApplicationUserService;
import com.example.brandstoftracker.service.abstracts.BrandStofService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BrandStofManagerTest {


    @Mock
    private  BrandStofRepository repository;
    @Mock
    private  ApplicationUserService userService;

    private BrandStofService underTest;


    private BrandStof brandStof;

    @BeforeEach
    void setUp() {
        brandStof=new BrandStof();
        brandStof.setBrandStofId(1L);
        brandStof.setBrandStofAmount(100L);
        brandStof.setPrice(BigDecimal.valueOf(100));
        underTest=new BrandStofManager(repository,userService);

    }

    @Test
    void getAll() {
        when(repository.findAll()).thenReturn(List.of(brandStof));
        List<BrandStof> test = underTest.getAll();
        assertEquals(1,test.size());
    }

    @Test
    void add() {
        when(repository.save(brandStof)).thenReturn(brandStof);
        BrandStof test = underTest.add(brandStof);
        assertEquals(1L,test.getBrandStofId());
        assertEquals(100L,test.getBrandStofAmount());
        assertEquals(BigDecimal.valueOf(100),test.getPrice());

    }

    @Test
    void getById() {
        when(repository.findById(1L)).thenReturn(Optional.of(brandStof));
        BrandStof test = underTest.getById(1L);
        assertEquals(1L,test.getBrandStofId());
        assertEquals(100L,test.getBrandStofAmount());
        assertEquals(BigDecimal.valueOf(100),test.getPrice());
    }

    @Test
    void findAllByAssignedAuto_AutoId() {
        when(repository.findAllByAssignedAuto_AutoIdOrderByRefuelingDateDesc(1L)).thenReturn(List.of(brandStof));
        List<BrandStof> test = underTest.findAllByAssignedAuto_AutoId(1L);
        assertEquals(1,test.size());
    }

    @Test
    void getTotalCost() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        LocalDateTime time = LocalDateTime.of(2022, 01, 01, 0, 0);
        TotalCostResponse response=new TotalCostResponse();
        response.setTotalCost(BigDecimal.valueOf(100));
        response.setCountOfRefueling(100l);
        when(repository.getTotalCost(1l,1l, time)).thenReturn(response);
        TotalCostResponse test = underTest.getTotalCost(1L, LocalDate.of(2022,01,01));

        assertEquals(BigDecimal.valueOf(100),test.getTotalCost());
        assertEquals(100L,test.getCountOfRefueling());
    }

    @Test
    void getTotalCostAlltime() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        TotalCostResponse response=new TotalCostResponse();
        response.setTotalCost(BigDecimal.valueOf(100));
        response.setCountOfRefueling(100l);
        when(repository.getTotalCostAllTime(1L,1L)).thenReturn(response);
        TotalCostResponse test = underTest.getTotalCostAlltime(1l);
        assertEquals(BigDecimal.valueOf(100),test.getTotalCost());
        assertEquals(100L,test.getCountOfRefueling());
    }

    @Test
    void getTotalCostAlltimeAllautos() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        TotalBrandCostOfAllAutosByUserId response=new TotalBrandCostOfAllAutosByUserId();
        response.setTotalCost(BigDecimal.valueOf(100));
        when(repository.getTotalCostAlltimeAllautos(1L)).thenReturn(response);
        TotalBrandCostOfAllAutosByUserId test = underTest.getTotalCostAlltimeAllautos();

        assertEquals(BigDecimal.valueOf(100),test.getTotalCost());


    }


    @Test
    void totalBrandCostOfAllAutosByUserIdForMonths() {
        Authentication authentication = Mockito.mock(Authentication.class);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("admin");
        ApplicationUser user = new ApplicationUser();
        user.setUserId(1L);
        user.setEmail("admin");
        when(userService.findByEmail("admin")).thenReturn(user);
        SecurityContextHolder.setContext(securityContext);
        LocalDateTime time = LocalDateTime.of(2022,1,1,1,1);
        TotalCostForMonths response=new TotalCostForMonths() {
            @Override
            public String getMonth() {
                return "May";
            }

            @Override
            public BigDecimal getPrice() {
                return BigDecimal.valueOf(100);
            }
        };
        when(repository.getTotalCostForEachMonth(1L)).thenReturn(List.of(response));
        List<TotalCostForMonths> test = underTest.getTotalCostAlltimeAllautosForMonths();
        assertEquals(1,test.size());

    }

}
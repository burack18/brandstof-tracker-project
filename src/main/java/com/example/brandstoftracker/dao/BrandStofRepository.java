package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostForMonths;
import com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse;
import com.example.brandstoftracker.domain.BrandStof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BrandStofRepository extends JpaRepository<BrandStof,Long> {
    List<BrandStof> findAllByAssignedAuto_AutoIdOrderByRefuelingDateDesc(Long autoId);

    @Query("SELECT new com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse(sum(b.price),count(b),b.assignedAuto.plateNumber)  from BrandStof b where b.refuelingDate>:date and b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.autoId,b.assignedAuto.plateNumber having b.assignedAuto.autoId=:autoId")
    TotalCostResponse getTotalCost(@Param("autoId")Long autoId,@Param("userId")Long userId, @Param("date") LocalDateTime date);

    @Query("SELECT new com.example.brandstoftracker.api.dto.brandstofDtos.TotalCostResponse(sum(b.price),count(b),b.assignedAuto.plateNumber)  from BrandStof b where b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.autoId,b.assignedAuto.plateNumber having b.assignedAuto.autoId=:autoId")
    TotalCostResponse getTotalCostAllTime(@Param("autoId")Long autoId,@Param("userId")Long userId);

    @Query("SELECT new com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId(sum(b.price),count(b)) from BrandStof b where b.assignedAuto.assignedUser.userId=:userId")
    TotalBrandCostOfAllAutosByUserId getTotalCostAlltimeAllautos(@Param("userId")Long userId);

    @Query("SELECT new com.example.brandstoftracker.api.dto.brandstofDtos.TotalBrandCostOfAllAutosByUserId(sum(b.price),count(b)) from BrandStof b where b.assignedAuto.assignedUser.userId=:userId and  b.refuelingDate>:date")
    TotalBrandCostOfAllAutosByUserId getTotalCostAlltimeAllautosForMonths(@Param("date")LocalDateTime date,@Param("userId")Long userId);

    @Query(nativeQuery = true,value = "select to_char(a.refueling_date,'month') as month,sum(a.price) as price from brand_stof a join auto a2 on a2.auto_id = a.auto_id where a2.user_id=:userId group by to_char(a.refueling_date,'month'),to_char(a.refueling_date,'year') order by to_char(a.refueling_date,'month') asc")
    List<TotalCostForMonths> getTotalCostForEachMonth(@Param("userId")Long userId);

    @Query(nativeQuery = true,value = "select to_char(a.refueling_date,'month') as month,sum(a.price) as price from brand_stof a join auto a2 on a2.auto_id = a.auto_id where a.refueling_date>=:date and a2.user_id=:userId  group by to_char(a.refueling_date,'month'),to_char(a.refueling_date,'year') order by to_char(a.refueling_date,'month') asc ")
    List<TotalCostForMonths> getTotalCostForEachMonthAfterDate(@Param("date")LocalDateTime date,@Param("userId")Long userId);
}

package com.example.brandstoftracker.dao;

import com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse;
import com.example.brandstoftracker.domain.AutoUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AutoUsageRepository extends JpaRepository<AutoUsage,Long> {
    List<AutoUsage> findAllByAssignedAuto_AutoIdOrderByDateDesc(Long autoId);

    @Query("SELECT new com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse(sum(b.distance),count(b),sum(b.brandStofVerbruik),b.assignedAuto.plateNumber)  from AutoUsage b where b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.autoId,b.assignedAuto.plateNumber having b.assignedAuto.autoId=:autoId")
    TotalAutoUsageResponse getTotalCostAllTime(@Param("autoId")Long autoId,@Param("userId") Long userId);

    @Query("SELECT new com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse(sum(b.distance),count(b),sum(b.brandStofVerbruik),b.assignedAuto.plateNumber)  from AutoUsage b where b.date>=:date and b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.autoId,b.assignedAuto.plateNumber having b.assignedAuto.autoId=:autoId")
    TotalAutoUsageResponse getTotalAutoUsageCostByAutoIdAllTime(@Param("autoId")Long autoId,@Param("userId") Long userId, @Param("date") LocalDate date);

    @Query("SELECT new com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse(sum(b.distance),count(b),sum(b.brandStofVerbruik),'')  from AutoUsage b where b.date>=:date and b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.assignedUser.userId")
    TotalAutoUsageResponse getTotalAutoUsageCostAllTime(@Param("userId") Long userId, @Param("date") LocalDate date);

    @Query("SELECT new com.example.brandstoftracker.api.dto.autousageDtos.TotalAutoUsageResponse(sum(b.distance),count(b),sum(b.brandStofVerbruik),'')  from AutoUsage b where b.assignedAuto.assignedUser.userId=:userId group by b.assignedAuto.assignedUser.userId")
    TotalAutoUsageResponse getTotalAutoUsageCostAllTime(Long userId);
}

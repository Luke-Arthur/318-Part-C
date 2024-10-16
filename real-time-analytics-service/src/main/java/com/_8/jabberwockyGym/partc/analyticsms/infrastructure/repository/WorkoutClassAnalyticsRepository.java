package com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository;

import com._8.jabberwockyGym.partc.analyticsms.domain.model.WorkoutClassAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutClassAnalyticsRepository extends JpaRepository<WorkoutClassAnalytics, Long> {
    WorkoutClassAnalytics findByClassId(Long classId);  // Find class by its ID
}

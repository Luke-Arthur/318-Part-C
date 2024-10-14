package com.CSCI318.workoutclass_service.workoutclassms.infrastructure.repository;

import com.CSCI318.workoutclass_service.workoutclassms.domain.model.WorkoutClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutClassRepository extends JpaRepository<WorkoutClass, Long> {
}

package com.CSCI318.workoutclass_service.repository;

import com.CSCI318.workoutclass_service.model.WorkoutClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutClassRepository extends JpaRepository<WorkoutClass, Long> {
}

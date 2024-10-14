package com.CSCI318.workoutclass_service.service;

import com.CSCI318.workoutclass_service.model.WorkoutClass;
import com.CSCI318.workoutclass_service.repository.WorkoutClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkoutClassService {
    @Autowired
    private WorkoutClassRepository workoutClassRepository;

    public List<WorkoutClass> getAllWorkoutClasses() {
        return workoutClassRepository.findAll();
    }

    public WorkoutClass getWorkoutClassById(Long id) {
        return workoutClassRepository.findById(id).orElse(null);
    }

    public WorkoutClass createOrUpdateWorkoutClass(WorkoutClass workoutClass) {
        return workoutClassRepository.save(workoutClass);
    }

    public void deleteWorkoutClass(Long id) {
        workoutClassRepository.deleteById(id);
    }
}

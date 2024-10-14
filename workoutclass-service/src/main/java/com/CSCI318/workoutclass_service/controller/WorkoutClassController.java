package com.CSCI318.workoutclass_service.controller;

import com.CSCI318.workoutclass_service.service.WorkoutClassService;
import com.CSCI318.workoutclass_service.model.WorkoutClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workoutclasses")
public class WorkoutClassController {
    @Autowired
    private WorkoutClassService workoutClassService;

    @GetMapping
    public ResponseEntity<List<WorkoutClass>> getAllWorkoutClasses() {
        return new ResponseEntity<>(workoutClassService.getAllWorkoutClasses(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkoutClass> getWorkoutClassById(@PathVariable Long id) {
        return new ResponseEntity<>(workoutClassService.getWorkoutClassById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkoutClass> createOrUpdateWorkoutClass(@RequestBody WorkoutClass workoutClass) {
        return new ResponseEntity<>(workoutClassService.createOrUpdateWorkoutClass(workoutClass), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkoutClass(@PathVariable Long id) {
        workoutClassService.deleteWorkoutClass(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


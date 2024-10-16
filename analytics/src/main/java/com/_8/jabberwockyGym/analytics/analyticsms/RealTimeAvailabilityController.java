package com._8.jabberwockyGym.analytics.analyticsms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RealTimeAvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/availability/{classId}")
    public ResponseEntity<Long> getClassAvailability(@PathVariable String classId) {
        Long availability = availabilityService.getClassAvailability(classId);
        return ResponseEntity.ok(availability);
    }
}

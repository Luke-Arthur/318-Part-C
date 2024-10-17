package com._8.jabberwockyGym.analytics.analyticsms.interfaces.controller;

import com._8.jabberwockyGym.analytics.analyticsms.application.RealTimeAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingCounterController {

    @Autowired
    private RealTimeAnalysisService realTimeAnalysisService;

    @GetMapping("/bookings/created")
    public ResponseEntity<Long> getTotalBookingsCreated() {
        return ResponseEntity.ok(realTimeAnalysisService.getTotalBookingsCreated());
    }

    @GetMapping("/bookings/cancelled")
    public ResponseEntity<Long> getTotalBookingsCancelled() {
        return ResponseEntity.ok(realTimeAnalysisService.getTotalBookingsCancelled());
    }
}

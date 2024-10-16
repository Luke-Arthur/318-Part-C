package com._8.jabberwockyGym.partc.analyticsms.interfaces.rest.controller;

import com._8.jabberwockyGym.partc.analyticsms.application.BookingAnalyticsService;
import com._8.jabberwockyGym.partc.analyticsms.domain.model.BookingAnalytics;
import com._8.jabberwockyGym.partc.analyticsms.domain.model.WorkoutClassAnalytics;
import com._8.jabberwockyGym.partc.analyticsms.domain.model.MemberActivityAnalytics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class BookingAnalyticsController {

    @Autowired
    private BookingAnalyticsService bookingAnalyticsService;

    // Endpoint to get all analytics
    @GetMapping("/all")
    public ResponseEntity<List<BookingAnalytics>> getAllAnalytics() {
        return ResponseEntity.ok(bookingAnalyticsService.getAllAnalytics());
    }

    // Endpoint to get class availability for a specific class
    @GetMapping("/class-availability/{classId}")
    public ResponseEntity<WorkoutClassAnalytics> getClassAvailability(@PathVariable Long classId) {
        WorkoutClassAnalytics classAnalytics = bookingAnalyticsService.getClassAvailability(classId);
        if (classAnalytics == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(classAnalytics);
    }

    // Endpoint to get member activity for a specific member
    @GetMapping("/member-activity/{memberId}")
    public ResponseEntity<MemberActivityAnalytics> getMemberActivity(@PathVariable Long memberId) {
        MemberActivityAnalytics memberActivity = bookingAnalyticsService.getMemberActivity(memberId);
        if (memberActivity == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(memberActivity);
    }
}

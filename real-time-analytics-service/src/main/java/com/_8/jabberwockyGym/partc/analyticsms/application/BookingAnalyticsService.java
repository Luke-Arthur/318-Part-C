package com._8.jabberwockyGym.partc.analyticsms.application;

import com._8.jabberwockyGym.partc.analyticsms.domain.model.BookingAnalytics;
import com._8.jabberwockyGym.partc.analyticsms.domain.model.MemberActivityAnalytics;
import com._8.jabberwockyGym.partc.analyticsms.domain.model.WorkoutClassAnalytics;
import com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository.BookingAnalyticsRepository;
import com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository.MemberActivityAnalyticsRepository;
import com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository.WorkoutClassAnalyticsRepository;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCreatedEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCancelledEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class BookingAnalyticsService {

    @Autowired
    private BookingAnalyticsRepository bookingAnalyticsRepository;

    @Autowired
    private WorkoutClassAnalyticsRepository workoutClassAnalyticsRepository;

    @Autowired
    private MemberActivityAnalyticsRepository memberActivityAnalyticsRepository;

    @Bean
    public Consumer<BookingCreatedEvent> processBookingCreated() {
        return event -> {
            // Save booking analytics
            saveAnalytics(event.getBookingId(), "BookingCreated", event.getEventTime(), event.getMemberEmail());

            // Decrement class availability
            updateClassAvailability(event.getClassId(), "BookingCreated");

            // Track member activity
            updateMemberActivity(event.getMemberId(), "BookingCreated");
        };
    }

    @Bean
    public Consumer<BookingCancelledEvent> processBookingCancelled() {
        return event -> {
            // Save booking analytics
            saveAnalytics(event.getBookingId(), "BookingCancelled", event.getEventTime(), event.getMemberEmail());

            // Increment class availability
            updateClassAvailability(event.getClassId(), "BookingCancelled");

            // Track member activity
            updateMemberActivity(event.getMemberId(), "BookingCancelled");
        };
    }

    private void saveAnalytics(Long bookingId, String eventType, LocalDateTime eventTime, String memberEmail) {
        BookingAnalytics analytics = new BookingAnalytics();
        analytics.setBookingId(bookingId);
        analytics.setEventType(eventType);
        analytics.setEventTime(eventTime);
        analytics.setMemberEmail(memberEmail);
        bookingAnalyticsRepository.save(analytics);
    }

    private void updateClassAvailability(Long classId, String eventType) {
        WorkoutClassAnalytics classAnalytics = workoutClassAnalyticsRepository.findByClassId(classId);
        if (classAnalytics == null) {
            classAnalytics = new WorkoutClassAnalytics();
            classAnalytics.setClassId(classId);
            classAnalytics.setAvailableSpots(20); // Assume a default number of spots
        }

        if ("BookingCreated".equals(eventType)) {
            classAnalytics.setAvailableSpots(classAnalytics.getAvailableSpots() - 1);
        } else if ("BookingCancelled".equals(eventType)) {
            classAnalytics.setAvailableSpots(classAnalytics.getAvailableSpots() + 1);
        }
        workoutClassAnalyticsRepository.save(classAnalytics);
    }

    private void updateMemberActivity(Long memberId, String eventType) {
        MemberActivityAnalytics memberActivity = memberActivityAnalyticsRepository.findByMemberId(memberId);
        if (memberActivity == null) {
            memberActivity = new MemberActivityAnalytics();
            memberActivity.setMemberId(memberId);
            memberActivity.setTotalBookings(0);
            memberActivity.setTotalCancellations(0);
        }

        if ("BookingCreated".equals(eventType)) {
            memberActivity.setTotalBookings(memberActivity.getTotalBookings() + 1);
        } else if ("BookingCancelled".equals(eventType)) {
            memberActivity.setTotalCancellations(memberActivity.getTotalCancellations() + 1);
        }
        memberActivityAnalyticsRepository.save(memberActivity);
    }

    public List<BookingAnalytics> getAllAnalytics() {
        return bookingAnalyticsRepository.findAll();
    }

    // Get class availability by class ID
    public WorkoutClassAnalytics getClassAvailability(Long classId) {
        return workoutClassAnalyticsRepository.findByClassId(classId);
    }

    // Get member activity by member ID
    public MemberActivityAnalytics getMemberActivity(Long memberId) {
        return memberActivityAnalyticsRepository.findByMemberId(memberId);
    }
}

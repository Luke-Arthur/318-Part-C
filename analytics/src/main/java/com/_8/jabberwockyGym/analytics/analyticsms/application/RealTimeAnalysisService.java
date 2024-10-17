package com._8.jabberwockyGym.analytics.analyticsms.application;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.apache.kafka.streams.kstream.KStream;

import java.util.function.Consumer;

@Service
public class RealTimeAnalysisService {

    private long totalBookingsCreated = 0L;
    private long totalBookingsCancelled = 0L;

    // booking-created topic
    @Bean
    public Consumer<KStream<String, String>> bookingCreatedInput() {
        return input -> input.foreach((key, value) -> {
            totalBookingsCreated++;
            System.out.println("Received Booking Created Event. Total bookings created: " + totalBookingsCreated);
        });
    }

    // handle booking-cancelled topic
    @Bean
    public Consumer<KStream<String, String>> bookingCancelledInput() {
        return input -> input.foreach((key, value) -> {
            totalBookingsCancelled++;
            System.out.println("Received Booking Cancelled Event. Total bookings cancelled: " + totalBookingsCancelled);
        });
    }

    // Getter methods for booking counts
    public long getTotalBookingsCreated() {
        return totalBookingsCreated;
    }

    public long getTotalBookingsCancelled() {
        return totalBookingsCancelled;
    }
}

package com.CSCI318.booking_service.bookingms.eventHandler;

import com.CSCI318.booking_service.bookingms.application.outboundservices.KafkaProducer;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCancelledEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCreatedEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventHandler {

    @Autowired
    private KafkaProducer kafkaProducer;

    // Static variable to keep track of the booking count
    private static long bookingCount = 0;

    // Static variable to store the previous booking ID
    private static Long previousBookingId = null;

    @EventListener
    public void handleBookingCreated(BookingCreatedEvent event) {

        Long currentBookingId = event.getBookingId();

        // Increment the booking count whenever the event is triggered
        bookingCount++;

        // creating  a line break for better readability
        System.out.println("=====================================================================");
        System.out.println("Booking Created Event: " + bookingCount + " has been triggered");
        System.out.println("=====================================================================");

        // Check if the booking ID is the same as the previous one
        if (previousBookingId != null && previousBookingId.equals(currentBookingId)) {
            // Log a message indicating that an update might have occurred
            System.out.println("Warning: Booking ID " + currentBookingId + " has been triggered again. It may have been updated but not created as a new booking.");
        } else {
            // log the booking creation
            System.out.println("Booking Created with ID: " + currentBookingId);
            System.out.println("Booking Time: " + event.getEventTime());

            // Notionally email the member with the booking ID
            System.out.println("Notional Email confirmation sent to member with booking ID: " + currentBookingId);
        }

        // Update the previous booking ID for future comparisons
        previousBookingId = currentBookingId;

        System.out.println("=====================================================================");
    }

    @EventListener
    public void handleBookingUpdated(BookingUpdatedEvent event) {
        System.out.println("=====================================================================");
        System.out.println("Booking Updated Event for Booking ID: " + event.getBookingId());
        System.out.println("Booking Update Time: " + event.getEventTime());

        // Notionally email the member about the update
        System.out.println("Notional Email sent to member regarding the update for booking ID: " + event.getBookingId());

        // Send Kafka message for booking updated
        kafkaProducer.sendMessage("booking-updated-topic", String.valueOf(event));

        System.out.println("=====================================================================");
    }

    @EventListener
    public void handleBookingCancelled(BookingCancelledEvent event) {
        System.out.println("=====================================================================");
        System.out.println("Booking Cancelled Event for Booking ID: " + event.getBookingId());
        System.out.println("Booking Cancel Time: " + event.getEventTime());

        // Notionally email the member about the cancellation
        System.out.println("Notional Email sent to member regarding the cancellation of booking ID: " + event.getBookingId());

        // Send Kafka message for booking cancelled
        kafkaProducer.sendMessage("booking-cancelled-topic", String.valueOf(event));

        System.out.println("=====================================================================");
    }



}

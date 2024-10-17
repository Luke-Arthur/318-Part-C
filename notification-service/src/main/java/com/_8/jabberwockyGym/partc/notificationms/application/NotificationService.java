package com._8.jabberwockyGym.partc.notificationms.application;

import com.CSCI318.booking_service.shareddomain.events.event.BookingCancelledEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCreatedEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingUpdatedEvent;
import com._8.jabberwockyGym.partc.notificationms.domain.model.Notification;
import com._8.jabberwockyGym.partc.notificationms.infrastructure.repository.NotificationRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ObjectMapper objectMapper;

    // Handle BookingCreated Event
    @KafkaListener(topics = "booking-created", groupId = "notification-group")
    public void handleBookingCreated(String message) {
        try {
            // Deserialising the message into BookingCreatedEvent
            BookingCreatedEvent event = objectMapper.readValue(message, BookingCreatedEvent.class);
            Notification notification = new Notification();
            notification.setMessage("Booking Created for Booking ID: " + event.getBookingId());
            notification.setEventType("BookingCreated");
            notification.setMemberEmail(event.getMemberEmail());
            notification.setEventTime(event.getEventTime());
            notificationRepository.save(notification);
            System.out.println("Booking Created Notification saved: " + event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle BookingUpdated Event
    @KafkaListener(topics = "booking-updated", groupId = "notification-group")
    public void handleBookingUpdated(String message) {
        try {
            // Deserialising the message into BookingUpdatedEvent
            BookingUpdatedEvent event = objectMapper.readValue(message, BookingUpdatedEvent.class);
            Notification notification = new Notification();
            notification.setMessage("Booking Updated for Booking ID: " + event.getBookingId());
            notification.setEventType("BookingUpdated");
            notification.setMemberEmail(event.getMemberEmail());
            notification.setEventTime(event.getEventTime());

            notificationRepository.save(notification);
            System.out.println("Booking Updated Notification saved: " + event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Handle BookingCancelled Event
    @KafkaListener(topics = "booking-cancelled", groupId = "notification-group")
    public void handleBookingCancelled(String message) {
        try {
            // Deserialize the message into BookingCancelledEvent
            BookingCancelledEvent event = objectMapper.readValue(message, BookingCancelledEvent.class);
            Notification notification = new Notification();
            notification.setMessage("Booking Cancelled for Booking ID: " + event.getBookingId());
            notification.setEventType("BookingCancelled");
            notification.setMemberEmail(event.getMemberEmail());
            notification.setEventTime(event.getEventTime());

            notificationRepository.save(notification);
            System.out.println("Booking Cancelled Notification saved: " + event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

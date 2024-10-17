package com.CSCI318.booking_service.shareddomain.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingCreatedEvent {

    private final Long bookingId;
    private final String memberEmail;
    private final LocalDateTime eventTime;
    private final String eventType;

    public BookingCreatedEvent() {
        this.bookingId = null;
        this.memberEmail = null;
        this.eventTime = null;
        this.eventType = null;
    }

    // Constructor with bookingId, memberEmail, and classId
    public BookingCreatedEvent(Long bookingId, String memberEmail) {
        this.bookingId = bookingId;
        this.memberEmail = memberEmail;
        this.eventTime = LocalDateTime.now();
        this.eventType = "created";
    }
}

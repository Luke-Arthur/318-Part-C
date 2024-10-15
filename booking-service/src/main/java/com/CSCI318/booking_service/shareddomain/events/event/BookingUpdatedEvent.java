package com.CSCI318.booking_service.shareddomain.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingUpdatedEvent {

    private final Long bookingId;
    private final LocalDateTime eventTime;
    private final String memberEmail;
    private final String eventType;

    // Default constructor
    public BookingUpdatedEvent() {
        this.bookingId = null;
        this.eventTime = null;
        this.memberEmail = null;
        this.eventType = null;
    }

    // All args constructor for updated event
    public BookingUpdatedEvent(Long bookingId, String memberEmail) {
        this.bookingId = bookingId;
        this.eventTime = LocalDateTime.now();
        this.memberEmail = memberEmail;
        this.eventType = "updated";
    }
}

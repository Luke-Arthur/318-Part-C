package com.CSCI318.booking_service.shareddomain.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor

public class BookingCancelledEvent {

    private final Long bookingId;
    private final String memberEmail;
    private final LocalDateTime eventTime;
    private final String eventType;

    // Default constructor for cancelled event
    public BookingCancelledEvent() {
        this.bookingId = null;
        this.eventTime = LocalDateTime.now();
        this.eventType = "cancelled";
        this.memberEmail = null;
    }

    // all args constructor for cancelled event
    public BookingCancelledEvent(Long bookingId, String memberEmail) {
        this.bookingId = bookingId;
        this.eventTime = LocalDateTime.now();
        this.eventType = "cancelled";
        this.memberEmail = memberEmail;
    }


}

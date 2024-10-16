package com.CSCI318.booking_service.shareddomain.events.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingCreatedEvent {

    private final Long bookingId;
    private final Long memberId; // Add memberId
    private final Long classId;  // Add classId
    private final String memberEmail;
    private final LocalDateTime eventTime;
    private final String eventType;

    public BookingCreatedEvent() {
        this.bookingId = null;
        this.memberId = null; // Add default for memberId
        this.classId = null;  // Add default for classId
        this.memberEmail = null;
        this.eventTime = null;
        this.eventType = null;
    }

    // Constructor with bookingId, memberId, classId, and memberEmail
    public BookingCreatedEvent(Long bookingId, Long memberId, Long classId, String memberEmail) {
        this.bookingId = bookingId;
        this.memberId = memberId; // Add memberId
        this.classId = classId;   // Add classId
        this.memberEmail = memberEmail;
        this.eventTime = LocalDateTime.now();
        this.eventType = "created";
    }
}

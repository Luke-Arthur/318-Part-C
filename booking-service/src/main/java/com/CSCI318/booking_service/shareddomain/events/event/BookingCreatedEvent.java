package com.CSCI318.booking_service.shareddomain.events.event;


import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BookingCreatedEvent {
    private final Long bookingId;
    private final LocalDateTime eventTime;

    // explicit constructor of BookingCreatedEvent Lombok won't get the event time
    public BookingCreatedEvent(Long bookingId) {
        this.bookingId = bookingId;
        this.eventTime = LocalDateTime.now();

    }

}


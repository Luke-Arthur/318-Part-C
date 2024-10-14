package com.CSCI318.booking_service.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Embeddable
@Getter
// This is our value object for booking time - we use this to ensure that the booking time is not in the past.
// It's immutable, so we can't change the booking time once it's set. which is good for ensuring that the booking time is accurate.
public class BookingTime {

    // the final keyword is used to make the time field immutable once it's set.
    // Also having no setter method for the time field makes it immutable.
    private final LocalDateTime time;

    public BookingTime(LocalDateTime time) {
        if (time.isBefore(LocalDateTime.now().minusSeconds(1))) {
            throw new IllegalArgumentException("Booking time cannot be in the past");
        }
        this.time = time;
    }

    public BookingTime() {
        this.time = LocalDateTime.now();
    }

    public String getFormattedBookingTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return time.format(formatter);
    }

    @Override
    public String toString() {
        return getFormattedBookingTime();
    }
}

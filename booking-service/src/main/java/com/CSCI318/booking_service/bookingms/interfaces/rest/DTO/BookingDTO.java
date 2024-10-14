package com.CSCI318.booking_service.bookingms.interfaces.rest.DTO;

import com.CSCI318.booking_service.bookingms.domain.model.Booking;
import com.CSCI318.booking_service.bookingms.domain.model.Member;
import com.CSCI318.booking_service.bookingms.domain.model.WorkoutClass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    // instance objects of Member and WorkoutClass
    private Member member;
    private WorkoutClass workoutClass;

    // instance variables of the entity Booking
    private Long bookingID;
    private LocalDateTime bookingTime;
    private String formattedBookingTime;

    // explicit constructor of BookingDTO Lombok won't get the formatted time
    public BookingDTO(Booking booking) {
        this.bookingID = booking.getId();
        this.member = booking.getMember();
        this.workoutClass = booking.getWorkoutClass();
        this.bookingTime = booking.getBookingTime().getTime();
        this.formattedBookingTime = booking.getBookingTime().getFormattedBookingTime();
    }
}

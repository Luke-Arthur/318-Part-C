//package com.CSCI318.member_service.model;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class Booking {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    private Long memberID;
//
//
//    private Long workoutClassID;
//
//    // backend / server side time
//    private LocalDateTime bookingTime;
//
//
//   // local date time formatting for booking time - to be used in our front end
//    public String getFormattedBookingTime() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        return bookingTime.format(formatter);
//    }
//}

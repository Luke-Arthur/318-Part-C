package com.CSCI318.booking_service.repository;

import com.CSCI318.booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByMember_Id(Long memberID);
    List<Booking> findByBookingTime_TimeBetween(LocalDateTime start, LocalDateTime end);
}


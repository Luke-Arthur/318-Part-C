package com.CSCI318.booking_service.bookingms.interfaces.rest.controller;


import com.CSCI318.booking_service.bookingms.interfaces.rest.DTO.BookingDTO;
import com.CSCI318.booking_service.bookingms.domain.model.Booking;
import com.CSCI318.booking_service.bookingms.application.BookingServices.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings()
                .stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        return ResponseEntity.ok(bookingDTO);
    }


    @GetMapping("/member/{memberID}")
    public ResponseEntity<List<BookingDTO>> getBookingsForMember(@PathVariable Long memberID) {
        List<BookingDTO> bookings = bookingService.getBookingsForMember(memberID)
                .stream()
                .map(booking -> {
                    BookingDTO bookingDTO = new BookingDTO();
                    bookingDTO.setBookingID(booking.getId());
                    bookingDTO.setBookingTime(booking.getBookingTime().getTime());
                    return bookingDTO;
                }).collect(Collectors.toList());
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody Booking booking) {
        Booking createdBooking = bookingService.createBooking(booking);
        if (createdBooking == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BookingDTO bookingDTO = new BookingDTO(createdBooking);
        return new ResponseEntity<>(bookingDTO, HttpStatus.CREATED);
    }

    //post bulk bookings
    @PostMapping("/bulk")
    public ResponseEntity<List<BookingDTO>> createBulkBookings(@RequestBody List<Booking> bookings) {
        List<Booking> createdBookings = bookingService.createBulkBookings(bookings);
        if (createdBookings == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<BookingDTO> bookingDTOs = createdBookings
                .stream()
                .map(BookingDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(bookingDTOs, HttpStatus.CREATED);
    }


    @GetMapping("/range")
    public ResponseEntity<List<Booking>> getBookingsInRange(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        return new ResponseEntity<>(bookingService.getBookingsInRange(start, end), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


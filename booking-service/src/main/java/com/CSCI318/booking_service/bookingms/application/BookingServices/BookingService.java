package com.CSCI318.booking_service.bookingms.application.BookingServices;

import com.CSCI318.booking_service.bookingms.application.outboundservices.KafkaProducer;
import com.CSCI318.booking_service.bookingms.interfaces.rest.DTO.BookingDTO;
import com.CSCI318.booking_service.bookingms.domain.model.Booking;
import com.CSCI318.booking_service.bookingms.domain.model.BookingTime;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCancelledEvent;
import com.CSCI318.booking_service.shareddomain.events.event.BookingCreatedEvent;
import com.CSCI318.booking_service.bookingms.infrastructure.repository.BookingRepository;
import com.CSCI318.booking_service.bookingms.domain.model.Member;
import com.CSCI318.booking_service.bookingms.domain.model.WorkoutClass;
import com.CSCI318.booking_service.shareddomain.events.event.BookingUpdatedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ObjectMapper objectMapper;


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsForMember(Long memberId) {
        return bookingRepository.findByMember_Id(memberId);
    }

    @Transactional
    public Booking createBooking(Booking booking) {
        try {
            // Refactored method to fetch member and workout class details and set them in the booking
            setMemberAndWorkoutClassDetails(booking);

            // Set the current time as booking time
            booking.setBookingTime(new BookingTime(LocalDateTime.now()));

            // Save the booking and publish the event
            Booking savedBooking = saveAndPublishBooking(booking);

         return savedBooking;

        } catch (EntityNotFoundException e) {
            // Handling the case where the member or workout class is not found
            System.err.println(e.getMessage());
            return null;
        }

    }

    @Transactional
    public List<Booking> createBulkBookings(List<Booking> bookings) {
        try {
            for (Booking booking : bookings) {
                // Refactored this method to fetch member and workout class details and set them in the booking
                setMemberAndWorkoutClassDetails(booking);

                // Set the current time as booking time
                booking.setBookingTime(new BookingTime(LocalDateTime.now()));

                // Save the booking and publish the event
                saveAndPublishBooking(booking);

            }
            return bookings;
        } catch (EntityNotFoundException e) {
            // Handling the case where the member or workout class is not found
            System.err.println(e.getMessage());
            return null;
        }
    }

    // Helper method to fetch member and workout class details and set them in the booking. Created this method to avoid code duplication with two create methods.
    private void setMemberAndWorkoutClassDetails(Booking booking) {
        // Fetch the member and workout class details from the respective services
        Member member = getMember(booking.getMember().getId());
        WorkoutClass workoutClass = getWorkoutClass(booking.getWorkoutClass().getId());

        // If the member or workout class is not found, an exception will be thrown in the above methods and caught in the create methods
        // So we can safely set the fetched details in the booking
        doesExist(member, booking.getMember().getId(), "Member", booking);
        doesExist(workoutClass, booking.getWorkoutClass().getId(), "Workout Class", booking);

        // Set the fetched details in the booking
        booking.setMember(member);
        booking.setWorkoutClass(workoutClass);
    }

    // my custom template method to check if the entity exists
    public <T> void doesExist(T entity, Long id, String entityName, Booking booking) {
        // If the entity is null, throw an exception
        if (entity == null) {
            throw new EntityNotFoundException("booking: " + booking.getId() + ", " + entityName + " with ID " + id + " not found. A booking cannot be created without a valid " + entityName + ".");
        }
    }


//    // Helper method to save the booking and publish the event (same same for both create methods)
    private Booking saveAndPublishBooking(Booking booking) {
        // Save the booking entity
        Booking savedBooking = bookingRepository.save(booking);

      try {
            String email = savedBooking.getMember().getEmail();
            String eventJson = objectMapper.writeValueAsString(new BookingCreatedEvent(savedBooking.getId(), email));
            kafkaProducer.sendMessage("booking-created", eventJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return savedBooking;
    }

    @Transactional
    public Booking updateBooking(Booking booking) {
        // Refactored method to fetch member and workout class details and set them in the booking
        setMemberAndWorkoutClassDetails(booking);

        // Update the booking entity
        Booking updatedBooking = bookingRepository.save(booking);

        try {
            String email = updatedBooking.getMember().getEmail();
            String eventJson = objectMapper.writeValueAsString(new BookingUpdatedEvent(updatedBooking.getId(), email));
            kafkaProducer.sendMessage("booking-updated", eventJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return updatedBooking;
    }



    // Our method to get the booking by ID. It returns a BookingDTO object so we can get the member and workout class details as well.
    public BookingDTO getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with ID: " + bookingId));

        return new BookingDTO(booking);
    }


    // Our method to get the bookings in a given range
    public List<Booking> getBookingsInRange(LocalDateTime start, LocalDateTime end) {
        return bookingRepository.findByBookingTime_TimeBetween(start, end);
    }



    // Method to handle deleting a booking and sending a Kafka event
    public void deleteBooking(Long id) {
        // Retrieve the booking before deleting
        BookingDTO booking = getBookingById(id);

        // Proceed to delete the booking
        bookingRepository.deleteById(id);

        // Send Kafka event after deletion
        try {
            String email = booking.getMember().getEmail();
            String eventJson = objectMapper.writeValueAsString(new BookingCancelledEvent(id, email));
            kafkaProducer.sendMessage("booking-cancelled", eventJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }




    // Fetching the member details from the member service
    public Member getMember(Long memberID) {
        final String url = "http://localhost:8081/api/members/";
        try {
            // we are using the restTemplate object to get the member object from the member service. passing the memberID as a parameter. so http://localhost:8081/api/members/1 for example.
            // this will return the member object with the ID of 1. which means the booking service will have the all the details of the member object with the ID of 1.
            return restTemplate.getForObject(url + memberID, Member.class);
        } catch (RestClientException e) {
            System.err.println("Failed to fetch member details: " + e.getMessage());
            throw new EntityNotFoundException("Member not found with ID: " + memberID);
        }
    }

    // Fetching the workout class details from the workout class service (@David - this basically does the same thing as the getMember method above)
    public WorkoutClass getWorkoutClass(Long workoutClassID) {
        final String url = "http://localhost:8083/api/workoutclasses/";
        try {
            // we are using the restTemplate object to get the workout class object from the workout class service. passing the workoutClassID as a parameter. so http://localhost:8083/api/workoutclasses/1 for example.
            return restTemplate.getForObject(url + workoutClassID, WorkoutClass.class);
        } catch (RestClientException e) {
            System.err.println("Failed to fetch workout class details: " + e.getMessage());
            throw new EntityNotFoundException("Workout class not found with ID: " + workoutClassID);
        }
    }

}

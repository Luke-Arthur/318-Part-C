package com.CSCI318.booking_service.bookingms;

import com.CSCI318.booking_service.bookingms.infrastructure.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	// Load the database with some initial data - this is for testing purposes. @David I've commented this out because It shouldn't be needed now unless we want to load one booking into the database always when the application starts.
	@Bean
	public CommandLineRunner loadDatabase(BookingRepository bookingRepository) throws Exception {
		return args -> {
//			 Booking entry = new Booking();
//			 entry.setMemberID(1L);
//			 entry.setWorkoutClassID(1L);
//			 BookingTime bookingTime = new BookingTime(LocalDateTime.now().plusMinutes(1));
//			 entry.setBookingTime(bookingTime);
//			 bookingRepository.save(entry);
//			 System.out.println(bookingRepository.findById(1L).orElseThrow());
		};
	}

}

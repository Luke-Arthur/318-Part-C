package com.CSCI318.booking_service.bookingms.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {
    @Setter
    @Id
    // primary key column of the Member table is id, which is generated automatically when a new Member entity is persisted to the database.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}


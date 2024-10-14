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
public class WorkoutClass {
    @Getter
    @Setter
    @Id
    // primary key column of the WorkoutClass table is id, which is generated automatically when a new WorkoutClass entity is persisted to the database.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String instructor;
    private String description;

}

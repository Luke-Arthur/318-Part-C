package com.CSCI318.booking_service.bookingms.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

public class Booking extends AbstractAggregateRoot<Booking> {

    @Id
    // The primary key column of the Booking table is id, which is generated automatically when a new Booking entity is persisted to the database.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // many-to-one relationship with Member entity in the domain model
    // So many bookings can be associated with one member, but one member can be associated with many bookings.
    // the cascade type is set to MERGE, which means that if a Member entity is updated, the changes will be propagated to the Booking entity.
    // the join column is set to member_id, which is the foreign key column in the Booking table that references the primary key column in the Member table.
    @ManyToOne(cascade = CascadeType.MERGE)

    @JoinColumn(name = "member_id")
    private Member member;

    // many-to-one relationship with WorkoutClass entity in the domain model
    // So many bookings can be associated with one workout class, but one workout class can be associated with many bookings.
    // the cascade type is set to MERGE, which means that if a WorkoutClass entity is updated, the changes will be propagated to the Booking entity.
    // the join column is set to workout_class_id, which is the foreign key column in the Booking table that references the primary key column in the WorkoutClass table.
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "workout_class_id")
    private WorkoutClass workoutClass;

    // This is our value object we are embedding in the Booking entity in the root aggregate of our domain model entity.
    @Embedded
    private BookingTime bookingTime;

}


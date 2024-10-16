package com._8.jabberwockyGym.partc.analyticsms.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@ToString
@Setter
@NoArgsConstructor
@Entity
public class BookingAnalytics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookingId;
    private String eventType;
    private LocalDateTime eventTime;
    private String memberEmail;

}

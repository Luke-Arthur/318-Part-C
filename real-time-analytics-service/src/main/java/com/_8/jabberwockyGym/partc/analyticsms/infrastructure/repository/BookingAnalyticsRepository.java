package com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository;

import com._8.jabberwockyGym.partc.analyticsms.domain.model.BookingAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingAnalyticsRepository extends JpaRepository<BookingAnalytics, Long> {
    // TODO: Add custom queries here
}


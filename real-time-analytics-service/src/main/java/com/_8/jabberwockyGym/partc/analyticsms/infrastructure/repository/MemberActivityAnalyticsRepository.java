package com._8.jabberwockyGym.partc.analyticsms.infrastructure.repository;

import com._8.jabberwockyGym.partc.analyticsms.domain.model.MemberActivityAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberActivityAnalyticsRepository extends JpaRepository<MemberActivityAnalytics, Long> {
    MemberActivityAnalytics findByMemberId(Long memberId);  // Find member activity by member ID
}

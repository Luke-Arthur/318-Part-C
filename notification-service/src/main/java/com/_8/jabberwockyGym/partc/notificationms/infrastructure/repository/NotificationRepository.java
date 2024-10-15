package com._8.jabberwockyGym.partc.notificationms.infrastructure.repository;

import com._8.jabberwockyGym.partc.notificationms.domain.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
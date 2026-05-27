package com.miniproject.be.domain.notification.repository;

import com.miniproject.be.domain.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Optional<Notification> findByUserId(Long userId);
}
package com.miniproject.be.domain.notification.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.notification.dto.NotificationSettingsRequest;
import com.miniproject.be.domain.notification.dto.NotificationSettingsResponse;
import com.miniproject.be.domain.notification.entity.Notification;
import com.miniproject.be.domain.notification.repository.NotificationRepository;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public NotificationSettingsResponse updateSettings(
            Long userId, NotificationSettingsRequest req) {

        Notification notification = notificationRepository.findByUserId(userId)
                .orElseGet(() -> createDefaultForUser(userId));

        notification.update(
                req.getDailyRecordEnabled(),
                req.getSpendingReviewEnabled(),
                req.getDailyRecordTime());

        return NotificationSettingsResponse.from(notification);
    }

    private Notification createDefaultForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        return notificationRepository.save(Notification.createDefault(user));
    }
}
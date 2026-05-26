package com.miniproject.be.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniproject.be.domain.notification.entity.Notification;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;


@Getter
@Builder
public class NotificationSettingsResponse {

    private boolean dailyRecordEnabled;
    private boolean spendingReviewEnabled;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime dailyRecordTime;

    public static NotificationSettingsResponse from(Notification notification) {
        return NotificationSettingsResponse.builder()
                .dailyRecordEnabled(notification.isDailyRecordEnabled())
                .spendingReviewEnabled(notification.isSpendingReviewEnabled())
                .dailyRecordTime(notification.getDailyRecordTime())
                .build();
    }
}
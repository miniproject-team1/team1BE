package com.miniproject.be.domain.notification.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Getter
@NoArgsConstructor
public class NotificationSettingsRequest {

    @NotNull(message = "일일 기록 알림 설정 값이 필요합니다.")
    private Boolean dailyRecordEnabled;

    @NotNull(message = "소비 회고 알림 설정 값이 필요합니다.")
    private Boolean spendingReviewEnabled;

    @NotNull(message = "알림 시간이 필요합니다.")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime dailyRecordTime;
}
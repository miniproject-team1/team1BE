package com.miniproject.be.domain.notification.entity;

import com.miniproject.be.common.BaseEntity;
import com.miniproject.be.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "daily_record_enabled", nullable = false)
    private boolean dailyRecordEnabled;

    @Column(name = "spending_review_enabled", nullable = false)
    private boolean spendingReviewEnabled;

    @Column(name = "daily_record_time", nullable = false)
    private LocalTime dailyRecordTime;

    @Builder
    private Notification(User user, boolean dailyRecordEnabled,
                         boolean spendingReviewEnabled, LocalTime dailyRecordTime) {
        this.user = user;
        this.dailyRecordEnabled = dailyRecordEnabled;
        this.spendingReviewEnabled = spendingReviewEnabled;
        this.dailyRecordTime = dailyRecordTime;
    }

    public static Notification createDefault(User user) {
        return Notification.builder()
                .user(user)
                .dailyRecordEnabled(true)
                .spendingReviewEnabled(true)
                .dailyRecordTime(LocalTime.of(20, 0))
                .build();
    }

    public void update(boolean dailyRecordEnabled,
                       boolean spendingReviewEnabled, LocalTime dailyRecordTime) {
        this.dailyRecordEnabled = dailyRecordEnabled;
        this.spendingReviewEnabled = spendingReviewEnabled;
        this.dailyRecordTime = dailyRecordTime;
    }
}
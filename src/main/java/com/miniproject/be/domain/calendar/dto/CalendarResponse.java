package com.miniproject.be.domain.calendar.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CalendarResponse {

    private int year;
    private int month;

    private int budgetAmount;     // 그 달 예산
    private int totalSpending;    // 그 달 총 소비
    private int remainingBudget;  // 예산 잔여액 (예산 - 총소비)

    private List<DayInfo> days;   // 일기가 있는 날짜들의 정보

    @Getter
    @Builder
    public static class DayInfo {
        private LocalDate date;     // 날짜
        private String emoji;       // 그 날 감정 이모지
        private boolean hasExpense; // 소비 기록이 있는 날인지
        private int daySpending;    // 그 날 총 소비 금액
    }
}
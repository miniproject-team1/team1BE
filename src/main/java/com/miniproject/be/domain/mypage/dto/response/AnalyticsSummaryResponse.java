package com.miniproject.be.domain.mypage.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AnalyticsSummaryResponse {

    private String period;          // 조회 기간 (this_month / last_month / three_months)
    private int totalSpending;      // 기간 총 소비 금액
    private int totalCount;         // 기간 총 소비 건수

    private int regretSpending;     // 후회 소비 금액 (만족도 2 이하)
    private int regretCount;        // 후회 소비 건수

    private int impulseCount;       // 충동 소비 건수 (이유: 충동/스트레스)
    private double impulseRatio;    // 충동 소비 비율 (%)

    private int budgetAmount;       // 해당 달 예산
    private double budgetUsedRatio; // 예산 달성률 (%)

    private List<EmotionSpendingCount> emotionCounts; // 감정별 소비 횟수

    @Getter
    @Builder
    public static class EmotionSpendingCount {
        private String emoji;   // 감정 이모지
        private long count;     // 그 감정일 때의 소비 건수
    }
}
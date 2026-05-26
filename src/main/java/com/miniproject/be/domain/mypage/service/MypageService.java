package com.miniproject.be.domain.mypage.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.diary.entity.Expense;
import com.miniproject.be.domain.diary.repository.ExpenseRepository;
import com.miniproject.be.domain.mypage.dto.response.AnalyticsSummaryResponse;
import com.miniproject.be.domain.mypage.dto.response.BudgetResponse;
import com.miniproject.be.domain.mypage.entity.Budget;
import com.miniproject.be.domain.mypage.repository.BudgetRepository;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    // 충동 소비로 간주할 소비 이유
    private static final Set<String> IMPULSE_REASONS = Set.of("충동", "스트레스");

    // 월별 예산 조회
    public BudgetResponse getBudget(Long userId, int year, int month) {
        int amount = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, year, month)
                .map(Budget::getAmount)
                .orElse(0);
        return new BudgetResponse(year, month, amount);
    }

    // 월별 예산 설정 및 수정
    @Transactional
    public BudgetResponse updateBudget(Long userId, int year, int month, int budgetAmount) {
        Budget budget = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, year, month)
                .orElse(null);

        if (budget == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            budget = new Budget(user, year, month, budgetAmount);
            budgetRepository.save(budget);
        } else {
            budget.changeAmount(budgetAmount);
        }
        return new BudgetResponse(year, month, budget.getAmount());
    }

    // 분석 데이터 요약 조회
    public AnalyticsSummaryResponse getAnalyticsSummary(Long userId, String period) {

        // 1. period로 조회 기간(시작일~종료일) 결정
        LocalDate today = LocalDate.now();
        LocalDate startDate;
        LocalDate endDate;

        switch (period) {
            case "last_month" -> {
                LocalDate lastMonth = today.minusMonths(1);
                startDate = lastMonth.withDayOfMonth(1);
                endDate = lastMonth.withDayOfMonth(lastMonth.lengthOfMonth());
            }
            case "three_months" -> {
                startDate = today.minusMonths(2).withDayOfMonth(1);
                endDate = today.withDayOfMonth(today.lengthOfMonth());
            }
            default -> { // "this_month" 및 그 외 모든 값
                startDate = today.withDayOfMonth(1);
                endDate = today.withDayOfMonth(today.lengthOfMonth());
            }
        }

        // 2. 기간 내 소비 전체 조회
        List<Expense> expenses =
                expenseRepository.findAllByUserAndPeriod(userId, startDate, endDate);

        // 3. 총 소비
        int totalSpending = expenses.stream().mapToInt(Expense::getAmount).sum();
        int totalCount = expenses.size();

        // 4. 후회 소비 (만족도 2 이하)
        List<Expense> regretExpenses = expenses.stream()
                .filter(e -> e.getSatisfaction() <= 2)
                .toList();
        int regretSpending = regretExpenses.stream().mapToInt(Expense::getAmount).sum();
        int regretCount = regretExpenses.size();

        // 5. 충동 소비 (이유: 충동/스트레스)
        int impulseCount = (int) expenses.stream()
                .filter(e -> e.getReason() != null && IMPULSE_REASONS.contains(e.getReason()))
                .count();
        double impulseRatio = totalCount == 0
                ? 0.0
                : Math.round((double) impulseCount / totalCount * 1000) / 10.0;

        // 6. 감정별 소비 횟수
        Map<String, Long> emotionMap = expenses.stream()
                .map(e -> e.getDiary().getEmoji())
                .filter(emoji -> emoji != null)
                .collect(Collectors.groupingBy(emoji -> emoji, Collectors.counting()));

        List<AnalyticsSummaryResponse.EmotionSpendingCount> emotionCounts = emotionMap.entrySet().stream()
                .map(entry -> AnalyticsSummaryResponse.EmotionSpendingCount.builder()
                        .emoji(entry.getKey())
                        .count(entry.getValue())
                        .build())
                .toList();

        // 7. 예산 달성률 (이번 달 예산 기준)
        int budgetAmount = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, today.getYear(), today.getMonthValue())
                .map(Budget::getAmount)
                .orElse(0);
        double budgetUsedRatio = budgetAmount == 0
                ? 0.0
                : Math.round((double) totalSpending / budgetAmount * 1000) / 10.0;

        // 8. 결과 조립
        return AnalyticsSummaryResponse.builder()
                .period(period)
                .totalSpending(totalSpending)
                .totalCount(totalCount)
                .regretSpending(regretSpending)
                .regretCount(regretCount)
                .impulseCount(impulseCount)
                .impulseRatio(impulseRatio)
                .budgetAmount(budgetAmount)
                .budgetUsedRatio(budgetUsedRatio)
                .emotionCounts(emotionCounts)
                .build();
    }
}
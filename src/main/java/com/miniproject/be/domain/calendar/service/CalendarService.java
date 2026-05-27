package com.miniproject.be.domain.calendar.service;

import com.miniproject.be.domain.calendar.dto.CalendarResponse;
import com.miniproject.be.domain.diary.entity.Diary;
import com.miniproject.be.domain.diary.repository.DiaryRepository;
import com.miniproject.be.domain.mypage.entity.Budget;
import com.miniproject.be.domain.mypage.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {

    private final DiaryRepository diaryRepository;
    private final BudgetRepository budgetRepository;

    // 월간 캘린더 조회
    public CalendarResponse getMonthlyCalendar(Long userId, int year, int month) {

        // 1. 그 달의 시작일 ~ 말일
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        // 2. 그 달 일기 전체 조회
        List<Diary> diaries =
                diaryRepository.findAllByUserAndPeriod(userId, startDate, endDate);

        // 3. 일기별 날짜 정보 만들기
        List<CalendarResponse.DayInfo> days = diaries.stream()
                .map(diary -> {
                    int daySpending = diary.getTotalExpense();
                    return CalendarResponse.DayInfo.builder()
                            .date(diary.getDiaryDate())
                            .emoji(diary.getEmoji())
                            .hasExpense(daySpending > 0)
                            .daySpending(daySpending)
                            .build();
                })
                .toList();

        // 4. 그 달 총 소비
        int totalSpending = diaries.stream()
                .mapToInt(Diary::getTotalExpense)
                .sum();

        // 5. 그 달 예산
        int budgetAmount = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, year, month)
                .map(Budget::getAmount)
                .orElse(0);

        // 6. 결과 조립
        return CalendarResponse.builder()
                .year(year)
                .month(month)
                .budgetAmount(budgetAmount)
                .totalSpending(totalSpending)
                .remainingBudget(budgetAmount - totalSpending)
                .days(days)
                .build();
    }
}
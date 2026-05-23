package com.miniproject.be.domain.mypage.service;

import com.miniproject.be.domain.mypage.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.miniproject.be.domain.mypage.dto.response.BudgetResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {

    private final MypageRepository mypageRepository;

    // 월별 예산 조회
    public BudgetResponse getBudget(int year, int month) {

        return new BudgetResponse(
                year,
                month,
                500000
        );
    }

    // 월별 예산 설정 및 수정
    @Transactional
    public BudgetResponse updateBudget(
            int year,
            int month,
            int budgetAmount
    ) {

        return new BudgetResponse(
                year,
                month,
                budgetAmount
        );
    }

    // 분석 데이터 요약 조회
    public Object getAnalyticsSummary(String period) {
        return null;
    }
}
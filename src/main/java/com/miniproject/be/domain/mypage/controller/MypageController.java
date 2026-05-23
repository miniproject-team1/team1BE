package com.miniproject.be.domain.mypage.controller;

import com.miniproject.be.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.miniproject.be.domain.mypage.dto.request.BudgetUpdateRequest;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 월별 예산 조회
    @GetMapping("/budgets/{year}/{month}")
    public ResponseEntity<?> getBudget(
            @PathVariable int year,
            @PathVariable int month
    ) {
        return ResponseEntity.ok(
                mypageService.getBudget(year, month)
        );
    }

    // 월별 예산 설정 및 수정
    @PutMapping("/budgets/{year}/{month}")
    public ResponseEntity<?> updateBudget(
            @PathVariable int year,
            @PathVariable int month,
            @RequestBody BudgetUpdateRequest request
    ) {
        return ResponseEntity.ok(
                mypageService.updateBudget(
                        year,
                        month,
                        request.getBudgetAmount()
                )
        );
    }

    // 분석 데이터 요약 조회
    @GetMapping("/analytics/summary")
    public ResponseEntity<?> getAnalyticsSummary(
            @RequestParam String period
    ) {
        return ResponseEntity.ok(
                mypageService.getAnalyticsSummary(period)
        );
    }
}
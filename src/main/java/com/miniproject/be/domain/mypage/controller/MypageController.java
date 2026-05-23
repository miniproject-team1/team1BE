package com.miniproject.be.domain.mypage.controller;

import com.miniproject.be.domain.mypage.dto.request.BudgetUpdateRequest;
import com.miniproject.be.domain.mypage.service.MypageService;
import com.miniproject.be.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 예산 조회
    @GetMapping("/budgets/{year}/{month}")
    public ResponseEntity<?> getBudget(
            @PathVariable int year,
            @PathVariable int month
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        mypageService.getBudget(year, month),
                        "예산 조회 성공"
                )
        );
    }

    // 예산 수정
    @PutMapping("/budgets/{year}/{month}")
    public ResponseEntity<?> updateBudget(
            @PathVariable int year,
            @PathVariable int month,
            @RequestBody BudgetUpdateRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        mypageService.updateBudget(year, month, request.getBudgetAmount()),
                        "예산 수정 성공"
                )
        );
    }

    // 분석
    @GetMapping("/analytics/summary")
    public ResponseEntity<?> getAnalyticsSummary(
            @RequestParam String period
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        mypageService.getAnalyticsSummary(period),
                        "분석 조회 성공"
                )
        );
    }
}
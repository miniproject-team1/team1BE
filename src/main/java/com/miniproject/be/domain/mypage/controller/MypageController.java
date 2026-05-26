package com.miniproject.be.domain.mypage.controller;

import com.miniproject.be.auth.CustomUserDetails;
import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.mypage.dto.request.BudgetUpdateRequest;
import com.miniproject.be.domain.mypage.dto.response.BudgetResponse;
import com.miniproject.be.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;


    @GetMapping("/budgets/{year}/{month}")
    public ResponseEntity<ApiResponse<BudgetResponse>> getBudget(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable int year,
            @PathVariable int month
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        mypageService.getBudget(userDetails.getUserId(), year, month),
                        "예산 조회 성공"
                )
        );
    }


    @PutMapping("/budgets/{year}/{month}")
    public ResponseEntity<ApiResponse<BudgetResponse>> updateBudget(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable int year,
            @PathVariable int month,
            @RequestBody BudgetUpdateRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        mypageService.updateBudget(
                                userDetails.getUserId(), year, month, request.getBudgetAmount()),
                        "예산 수정 성공"
                )
        );
    }


    @GetMapping("/analytics/summary")
    public ResponseEntity<ApiResponse<Object>> getAnalyticsSummary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestParam String period
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        mypageService.getAnalyticsSummary(userDetails.getUserId(), period),
                        "분석 조회 성공"
                )
        );
    }
}
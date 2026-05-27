package com.miniproject.be.domain.diary.controller;

import com.miniproject.be.auth.CustomUserDetails;
import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.diary.dto.*;
import com.miniproject.be.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<ApiResponse<DiaryCreateResponse>> createDiary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody DiaryCreateRequest request
    ) {
        DiaryCreateResponse response =
                diaryService.createDiary(userDetails.getUserId(), request);

        return ResponseEntity.ok(
                ApiResponse.success(response, "일기가 저장되었습니다.")
        );
    }

    @GetMapping("/{diary_date}")
    public ResponseEntity<ApiResponse<DiaryGetResponse>> getDiary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("diary_date") LocalDate diaryDate
    ) {
        DiaryGetResponse response =
                diaryService.getDiary(userDetails.getUserId(), diaryDate);

        return ResponseEntity.ok(
                ApiResponse.success(response)
        );
    }

    @PutMapping
    public ResponseEntity<ApiResponse<DiaryCreateResponse>> updateDiary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody DiaryCreateRequest request
    ) {
        DiaryCreateResponse response =
                diaryService.updateDiary(userDetails.getUserId(), request);

        return ResponseEntity.ok(
                ApiResponse.success(response, "일기가 수정되었습니다.")
        );
    }

    @DeleteMapping("/{diary_date}/expenses/{expenseId}")
    public ResponseEntity<ApiResponse<ExpenseDeleteResponse>> deleteExpense(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("diary_date") LocalDate diaryDate,
            @PathVariable Long expenseId
    ) {
        ExpenseDeleteResponse response =
                diaryService.deleteExpense(userDetails.getUserId(), diaryDate, expenseId);

        return ResponseEntity.ok(
                ApiResponse.success(response, "소비 항목이 삭제되었습니다.")
        );
    }

    @DeleteMapping("/{diary_date}")
    public ResponseEntity<ApiResponse<DiaryDeleteResponse>> deleteDiary(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable("diary_date") LocalDate diaryDate
    ) {
        DiaryDeleteResponse response =
                diaryService.deleteDiary(userDetails.getUserId(), diaryDate);

        return ResponseEntity.ok(
                ApiResponse.success(response, "일기가 삭제되었습니다.")
        );
    }
}
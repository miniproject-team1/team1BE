package com.miniproject.be.domain.diary.controller;

import com.miniproject.be.domain.diary.dto.*;
import com.miniproject.be.domain.diary.service.DiaryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.miniproject.be.domain.diary.dto.DiaryGetResponse;
import com.miniproject.be.domain.diary.dto.ExpenseDeleteResponse;
import com.miniproject.be.domain.diary.dto.DiaryDeleteResponse;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    public ResponseEntity<?> createDiary(
            @Valid @RequestBody DiaryCreateRequest request
    ) {

        DiaryCreateResponse response = diaryService.createDiary(request);

        return ResponseEntity.ok(
                Map.of(
                        "isSuccess", true,
                        "message", "일기가 저장되었습니다.",
                        "data", response
                )
        );
    }

    @GetMapping("/{diary_date}")
    public ResponseEntity<?> getDiary(
            @PathVariable("diary_date") LocalDate diaryDate
    ) {
        DiaryGetResponse response = diaryService.getDiary(diaryDate);

        return ResponseEntity.ok(
                Map.of(
                        "isSuccess", true,
                        "data", response
                )
        );
    }

    @PutMapping
    public ResponseEntity<?> updateDiary(
            @Valid @RequestBody DiaryCreateRequest request
    ) {
        DiaryCreateResponse response = diaryService.updateDiary(request);

        return ResponseEntity.ok(
                Map.of(
                        "isSuccess", true,
                        "message", "일기가 수정되었습니다.",
                        "data", response
                )
        );
    }

    @DeleteMapping("/{diary_date}/expenses/{expenseId}")
    public ResponseEntity<?> deleteExpense(
            @PathVariable("diary_date") LocalDate diaryDate,
            @PathVariable Long expenseId
    ) {
        ExpenseDeleteResponse response = diaryService.deleteExpense(diaryDate, expenseId);

        return ResponseEntity.ok(
                Map.of(
                        "isSuccess", true,
                        "message", "소비 항목이 삭제되었습니다.",
                        "data", response
                )
        );
    }

    @DeleteMapping("/{diary_date}")
    public ResponseEntity<?> deleteDiary(
            @PathVariable("diary_date") LocalDate diaryDate
    ) {
        DiaryDeleteResponse response = diaryService.deleteDiary(diaryDate);

        return ResponseEntity.ok(
                Map.of(
                        "isSuccess", true,
                        "message", "일기가 삭제되었습니다.",
                        "data", response
                )
        );
    }
}
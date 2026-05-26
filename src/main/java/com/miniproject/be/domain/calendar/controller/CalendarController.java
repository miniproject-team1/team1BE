package com.miniproject.be.domain.calendar.controller;

import com.miniproject.be.auth.CustomUserDetails;
import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.calendar.dto.CalendarResponse;
import com.miniproject.be.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // 월간 캘린더 조회
    @GetMapping("/calendar/{year}/{month}")
    public ResponseEntity<ApiResponse<CalendarResponse>> getMonthlyCalendar(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @PathVariable int year,
            @PathVariable int month
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        calendarService.getMonthlyCalendar(userDetails.getUserId(), year, month),
                        "캘린더 조회 성공"
                )
        );
    }
}
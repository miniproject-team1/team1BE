package com.miniproject.be.domain.notification.controller;

import com.miniproject.be.auth.CustomUserDetails;
import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.notification.dto.NotificationSettingsRequest;
import com.miniproject.be.domain.notification.dto.NotificationSettingsResponse;
import com.miniproject.be.domain.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;


    @PutMapping("/settings")
    public ResponseEntity<ApiResponse<NotificationSettingsResponse>> updateSettings(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody NotificationSettingsRequest request) {

        NotificationSettingsResponse response =
                notificationService.updateSettings(userDetails.getUserId(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
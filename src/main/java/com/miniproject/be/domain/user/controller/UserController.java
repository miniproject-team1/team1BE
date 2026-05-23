package com.miniproject.be.domain.user.controller;

import com.miniproject.be.auth.CustomUserDetails;
import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.user.dto.NicknameResponse;
import com.miniproject.be.domain.user.dto.NicknameUpdateRequest;
import com.miniproject.be.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PutMapping("/me/nickname")
    public ResponseEntity<ApiResponse<NicknameResponse>> updateNickname(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Valid @RequestBody NicknameUpdateRequest request) {

        NicknameResponse response =
                userService.updateNickname(userDetails.getUserId(), request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
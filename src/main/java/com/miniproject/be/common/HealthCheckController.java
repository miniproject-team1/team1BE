package com.miniproject.be.common;

import com.miniproject.be.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class HealthCheckController {

    @GetMapping("/health")
    public ApiResponse<String> health() {
        return ApiResponse.success("기분값 백엔드 서버가 정상 작동 중입니다");
    }
}
package com.miniproject.be.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class NicknameResponse {

    private Long userId;
    private String nickname;
    private LocalDateTime updatedAt;
}
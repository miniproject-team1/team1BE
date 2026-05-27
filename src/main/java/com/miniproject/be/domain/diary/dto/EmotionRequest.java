package com.miniproject.be.domain.diary.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class EmotionRequest {

    private String emoji;
    private String reason;
    private List<String> tags;
    private String memo;
}
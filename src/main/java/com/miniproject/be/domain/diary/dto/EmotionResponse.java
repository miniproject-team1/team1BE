package com.miniproject.be.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class EmotionResponse {

    private String emoji;
    private String reason;
    private List<String> tags;
    private String memo;
}
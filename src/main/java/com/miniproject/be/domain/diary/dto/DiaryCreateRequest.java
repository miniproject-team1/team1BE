package com.miniproject.be.domain.diary.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class DiaryCreateRequest {

    @NotNull
    private LocalDate diary_date;

    @Valid
    @NotNull
    private EmotionRequest emotion;

    @Valid
    private List<ExpenseRequest> expenses;
}
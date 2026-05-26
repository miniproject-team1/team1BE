package com.miniproject.be.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryCreateResponse {

    private LocalDate diary_date;
    private int totalExpense;
}
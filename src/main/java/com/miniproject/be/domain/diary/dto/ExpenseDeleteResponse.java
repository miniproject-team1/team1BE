package com.miniproject.be.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ExpenseDeleteResponse {

    private LocalDate diary_date;
    private Long expenseId;
}
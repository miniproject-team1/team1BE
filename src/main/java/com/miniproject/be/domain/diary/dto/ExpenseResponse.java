package com.miniproject.be.domain.diary.dto;

import com.miniproject.be.domain.diary.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExpenseResponse {

    private Long expenseId;
    private int amount;
    private String category;
    private String reason;
    private int satisfaction;

    public static ExpenseResponse from(Expense expense) {
        return new ExpenseResponse(
                expense.getId(),
                expense.getAmount(),
                expense.getCategory(),
                expense.getReason(),
                expense.getSatisfaction()
        );
    }
}
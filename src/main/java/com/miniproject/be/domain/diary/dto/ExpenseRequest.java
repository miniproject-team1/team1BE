package com.miniproject.be.domain.diary.dto;

import lombok.Getter;

@Getter
public class ExpenseRequest {

    private int amount;
    private String category;
    private String reason;
    private int satisfaction;
}
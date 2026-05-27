package com.miniproject.be.domain.mypage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BudgetResponse {

    private int year;
    private int month;
    private int budgetAmount;

}
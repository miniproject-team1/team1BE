package com.miniproject.be.domain.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishlistCreateResponse {

    private boolean success;
    private Long id;
    private boolean isExceedingBudget;
    private int remainingBudgetAfterPurchase;
    private String message;

}
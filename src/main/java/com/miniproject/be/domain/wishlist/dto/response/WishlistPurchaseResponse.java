package com.miniproject.be.domain.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishlistPurchaseResponse {

    private boolean success;
    private Long id;
    private boolean isPurchased;
    private String message;

}
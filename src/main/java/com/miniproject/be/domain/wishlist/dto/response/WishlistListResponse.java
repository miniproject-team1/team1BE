package com.miniproject.be.domain.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class WishlistListResponse {

    private int remainingBudget;
    private int totalWishPrice;
    private List<WishlistItemResponse> wishItems;

}
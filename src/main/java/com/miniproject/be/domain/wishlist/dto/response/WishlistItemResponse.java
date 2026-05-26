package com.miniproject.be.domain.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishlistItemResponse {

    private Long id;
    private String itemName;
    private int price;
    private String url;
    private boolean isPurchased;
    private String createdAt;

}
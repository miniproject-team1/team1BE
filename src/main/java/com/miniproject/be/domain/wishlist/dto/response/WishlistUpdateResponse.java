package com.miniproject.be.domain.wishlist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WishlistUpdateResponse {

    private boolean success;
    private String message;
    private WishlistItemResponse data;

}
package com.miniproject.be.domain.wishlist.dto.request;

import lombok.Getter;

@Getter
public class WishlistCreateRequest {

    private String itemName;
    private int price;
    private String url;

}
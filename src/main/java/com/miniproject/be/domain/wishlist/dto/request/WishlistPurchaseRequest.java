package com.miniproject.be.domain.wishlist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WishlistPurchaseRequest {

    @JsonProperty("purchased")
    private boolean purchased;

}
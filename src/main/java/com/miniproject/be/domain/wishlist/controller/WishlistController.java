package com.miniproject.be.domain.wishlist.controller;

import com.miniproject.be.domain.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

}
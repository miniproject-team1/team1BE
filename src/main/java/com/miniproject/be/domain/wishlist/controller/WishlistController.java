package com.miniproject.be.domain.wishlist.controller;

import com.miniproject.be.domain.wishlist.dto.request.*;
import com.miniproject.be.domain.wishlist.service.WishlistService;
import com.miniproject.be.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    // 전체 조회
    @GetMapping("/wishlist")
    public ResponseEntity<?> getWishlist() {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        wishlistService.getWishlist(),
                        "조회 성공"
                )
        );
    }

    // 등록
    @PostMapping("/wishlist")
    public ResponseEntity<?> createWishlist(@RequestBody WishlistCreateRequest request) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        wishlistService.createWishlist(request),
                        "등록 성공"
                )
        );
    }

    // 수정
    @PutMapping("/wishlist/{id}")
    public ResponseEntity<?> updateWishlist(
            @PathVariable Long id,
            @RequestBody WishlistUpdateRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        wishlistService.updateWishlist(id, request),
                        "수정 성공"
                )
        );
    }

    // 구매 상태 변경
    @PatchMapping("/wishlist/{id}/purchase")
    public ResponseEntity<?> purchaseWishlist(
            @PathVariable Long id,
            @RequestBody WishlistPurchaseRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        wishlistService.purchaseWishlist(id, request.isPurchased()),
                        "구매 상태 변경 성공"
                )
        );
    }

    // 삭제
    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<?> deleteWishlist(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.ok(
                        wishlistService.deleteWishlist(id),
                        "삭제 성공"
                )
        );
    }
}
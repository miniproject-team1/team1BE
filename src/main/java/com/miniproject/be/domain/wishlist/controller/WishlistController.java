package com.miniproject.be.domain.wishlist.controller;

import com.miniproject.be.common.response.ApiResponse;
import com.miniproject.be.domain.wishlist.dto.request.*;
import com.miniproject.be.domain.wishlist.dto.response.*;
import com.miniproject.be.domain.wishlist.service.WishlistService;
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
    public ResponseEntity<ApiResponse<WishlistListResponse>> getWishlist() {
        return ResponseEntity.ok(
                ApiResponse.success(
                        wishlistService.getWishlist(),
                        "조회 성공"
                )
        );
    }

    // 등록
    @PostMapping("/wishlist")
    public ResponseEntity<ApiResponse<WishlistCreateResponse>> createWishlist(
            @RequestBody WishlistCreateRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        wishlistService.createWishlist(request),
                        "등록 성공"
                )
        );
    }

    // 수정
    @PutMapping("/wishlist/{id}")
    public ResponseEntity<ApiResponse<WishlistUpdateResponse>> updateWishlist(
            @PathVariable Long id,
            @RequestBody WishlistUpdateRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        wishlistService.updateWishlist(id, request),
                        "수정 성공"
                )
        );
    }

    // 구매 상태 변경
    @PatchMapping("/wishlist/{id}/purchase")
    public ResponseEntity<ApiResponse<WishlistPurchaseResponse>> purchaseWishlist(
            @PathVariable Long id,
            @RequestBody WishlistPurchaseRequest request
    ) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        wishlistService.purchaseWishlist(id, request.isPurchased()),
                        "구매 상태 변경 성공"
                )
        );
    }

    // 삭제
    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<ApiResponse<WishlistDeleteResponse>> deleteWishlist(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        wishlistService.deleteWishlist(id),
                        "삭제 성공"
                )
        );
    }
}
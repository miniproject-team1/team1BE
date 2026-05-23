package com.miniproject.be.domain.wishlist.service;

import com.miniproject.be.domain.wishlist.dto.request.WishlistCreateRequest;
import com.miniproject.be.domain.wishlist.dto.request.WishlistUpdateRequest;
import com.miniproject.be.domain.wishlist.dto.response.*;
import com.miniproject.be.domain.wishlist.entity.Wishlist;
import com.miniproject.be.domain.wishlist.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    // 전체 조회
    public WishlistListResponse getWishlist() {

        List<Wishlist> entities = wishlistRepository.findAll();

        List<WishlistItemResponse> items = entities.stream()
                .map(w -> new WishlistItemResponse(
                        w.getId(),
                        w.getItemName(),
                        w.getPrice(),
                        w.getUrl(),
                        w.isPurchased(),
                        w.getCreatedAt().toString()
                ))
                .toList();

        int totalWishPrice = entities.stream()
                .mapToInt(Wishlist::getPrice)
                .sum();

        return new WishlistListResponse(
                500000,
                totalWishPrice,
                items
        );
    }

    // 등록
    @Transactional
    public WishlistCreateResponse createWishlist(WishlistCreateRequest request) {

        Wishlist wishlist = new Wishlist(
                request.getItemName(),
                request.getPrice(),
                request.getUrl()
        );

        Wishlist saved = wishlistRepository.save(wishlist);

        return new WishlistCreateResponse(
                true,
                saved.getId(),
                false,
                0,
                "등록 완료"
        );
    }

    // 수정
    @Transactional
    public WishlistUpdateResponse updateWishlist(Long id, WishlistUpdateRequest request) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 항목 없음"));

        wishlist.update(
                request.getItemName(),
                request.getPrice(),
                request.getUrl()
        );

        return new WishlistUpdateResponse(
                true,
                "수정 완료",
                new WishlistItemResponse(
                        wishlist.getId(),
                        wishlist.getItemName(),
                        wishlist.getPrice(),
                        wishlist.getUrl(),
                        wishlist.isPurchased(),
                        wishlist.getCreatedAt().toString()
                )
        );
    }

    // 구매 상태 변경
    @Transactional
    public WishlistPurchaseResponse purchaseWishlist(Long id, boolean isPurchased) {

        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 항목 없음"));

        wishlist.changePurchased(isPurchased);

        return new WishlistPurchaseResponse(
                true,
                id,
                isPurchased,
                "구매 상태 변경 완료"
        );
    }

    // 삭제
    @Transactional
    public WishlistDeleteResponse deleteWishlist(Long id) {

        wishlistRepository.deleteById(id);

        return new WishlistDeleteResponse(
                true,
                "삭제 완료"
        );
    }
}
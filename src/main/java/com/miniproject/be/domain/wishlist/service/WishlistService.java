package com.miniproject.be.domain.wishlist.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
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
    private final UserRepository userRepository;

    // 전체 조회 (내 것만)
    public WishlistListResponse getWishlist(Long userId) {

        List<Wishlist> entities = wishlistRepository.findAllByUserId(userId);

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
    public WishlistCreateResponse createWishlist(Long userId, WishlistCreateRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Wishlist wishlist = new Wishlist(
                user,
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
    public WishlistUpdateResponse updateWishlist(Long userId, Long id, WishlistUpdateRequest request) {

        Wishlist wishlist = findOwnedWishlist(userId, id);

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
    public WishlistPurchaseResponse purchaseWishlist(Long userId, Long id, boolean isPurchased) {

        Wishlist wishlist = findOwnedWishlist(userId, id);

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
    public WishlistDeleteResponse deleteWishlist(Long userId, Long id) {

        Wishlist wishlist = findOwnedWishlist(userId, id);

        wishlistRepository.delete(wishlist);

        return new WishlistDeleteResponse(
                true,
                "삭제 완료"
        );
    }

    // 위시리스트를 찾고, 그게 이 유저의 것인지 검증하는 공통 메서드
    private Wishlist findOwnedWishlist(Long userId, Long id) {
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.WISHLIST_NOT_FOUND));

        if (!wishlist.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.WISHLIST_ACCESS_DENIED);
        }
        return wishlist;
    }
}
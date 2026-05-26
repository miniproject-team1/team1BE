package com.miniproject.be.domain.wishlist.entity;

import com.miniproject.be.common.BaseEntity;
import com.miniproject.be.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "wishlists")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String itemName;

    private int price;

    private String url;

    private boolean isPurchased;

    public Wishlist(User user, String itemName, int price, String url) {
        this.user = user;
        this.itemName = itemName;
        this.price = price;
        this.url = url;
        this.isPurchased = false;
    }

    public void update(String itemName, int price, String url) {
        this.itemName = itemName;
        this.price = price;
        this.url = url;
    }

    public void changePurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}
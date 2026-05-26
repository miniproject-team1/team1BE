package com.miniproject.be.domain.wishlist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "wishlists")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wishlist_id")
    private Long id;

    private String itemName;

    private int price;

    private String url;

    private boolean isPurchased;

    private LocalDate createdAt;

    public Wishlist(String itemName, int price, String url) {
        this.itemName = itemName;
        this.price = price;
        this.url = url;
        this.isPurchased = false;
        this.createdAt = LocalDate.now();
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
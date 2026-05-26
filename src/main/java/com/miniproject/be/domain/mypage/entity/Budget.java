package com.miniproject.be.domain.mypage.entity;

import com.miniproject.be.common.BaseEntity;
import com.miniproject.be.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "budgets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_budget_user_year_month",
                        columnNames = {"user_id", "budget_year", "budget_month"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Budget extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "budget_year", nullable = false)
    private int year;

    @Column(name = "budget_month", nullable = false)
    private int month;

    @Column(name = "budget_amount", nullable = false)
    private int amount;

    public Budget(User user, int year, int month, int amount) {
        this.user = user;
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

    public void changeAmount(int amount) {
        this.amount = amount;
    }
}
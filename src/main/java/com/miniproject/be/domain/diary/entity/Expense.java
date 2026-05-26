package com.miniproject.be.domain.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expenses")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long id;

    private int amount;

    private String category;

    private String reason;

    private int satisfaction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    public Expense(int amount, String category, String reason, int satisfaction) {
        this.amount = amount;
        this.category = category;
        this.reason = reason;
        this.satisfaction = satisfaction;
    }

    public void setDiary(Diary diary) {
        this.diary = diary;
    }
}
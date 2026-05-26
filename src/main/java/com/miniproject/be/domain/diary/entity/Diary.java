package com.miniproject.be.domain.diary.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "diaries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private LocalDate diaryDate;

    private String emoji;

    private String emotionReason;

    private String emotionMemo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    public Diary(LocalDate diaryDate, String emoji, String emotionReason, List<String> tags, String emotionMemo) {
        this.diaryDate = diaryDate;
        this.emoji = emoji;
        this.emotionReason = emotionReason;
        this.emotionMemo = emotionMemo;

        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.setDiary(this);
    }

    public int getTotalExpense() {
        return expenses.stream()
                .mapToInt(Expense::getAmount)
                .sum();
    }

    public void update(String emoji, String emotionReason, List<String> tags, String emotionMemo) {
        this.emoji = emoji;
        this.emotionReason = emotionReason;
        this.tags.clear();

        if (tags != null) {
            this.tags.addAll(tags);
        }

        this.emotionMemo = emotionMemo;
    }

    public void clearExpenses() {
        this.expenses.clear();
    }

    public void removeExpense(Long expenseId) {
        expenses.removeIf(expense -> expense.getId().equals(expenseId));
    }
}
package com.miniproject.be.domain.diary.entity;

import com.miniproject.be.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "diaries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_diary_user_date",
                        columnNames = {"user_id", "diary_date"}
                )
        }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "diary_date", nullable = false)
    private LocalDate diaryDate;

    private String emoji;

    private String emotionReason;

    private String emotionMemo;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses = new ArrayList<>();

    public Diary(User user, LocalDate diaryDate, String emoji, String emotionReason,
                 List<String> tags, String emotionMemo) {
        this.user = user;
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
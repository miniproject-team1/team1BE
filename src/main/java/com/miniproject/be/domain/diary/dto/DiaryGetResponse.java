package com.miniproject.be.domain.diary.dto;

import com.miniproject.be.domain.diary.entity.Diary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DiaryGetResponse {

    private LocalDate diary_date;
    private EmotionResponse emotion;
    private List<ExpenseResponse> expenses;
    private int totalExpense;

    public static DiaryGetResponse from(Diary diary) {
        return new DiaryGetResponse(
                diary.getDiaryDate(),
                new EmotionResponse(
                        diary.getEmoji(),
                        diary.getEmotionReason(),
                        diary.getTags(),
                        diary.getEmotionMemo()
                ),
                diary.getExpenses().stream()
                        .map(ExpenseResponse::from)
                        .toList(),
                diary.getTotalExpense()
        );
    }
}
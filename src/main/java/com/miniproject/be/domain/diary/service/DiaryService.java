package com.miniproject.be.domain.diary.service;

import com.miniproject.be.domain.diary.dto.*;
import com.miniproject.be.domain.diary.entity.Diary;
import com.miniproject.be.domain.diary.entity.Expense;
import com.miniproject.be.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryCreateResponse createDiary(DiaryCreateRequest request) {

        if (diaryRepository.findByDiaryDate(request.getDiary_date()).isPresent()) {
            throw new IllegalArgumentException("이미 해당 날짜의 일기가 존재합니다.");
        }

        Diary diary = new Diary(
                request.getDiary_date(),
                request.getEmotion().getEmoji(),
                request.getEmotion().getReason(),
                request.getEmotion().getTags(),
                request.getEmotion().getMemo()
        );

        if (request.getExpenses() != null) {
            for (ExpenseRequest expenseRequest : request.getExpenses()) {

                Expense expense = new Expense(
                        expenseRequest.getAmount(),
                        expenseRequest.getCategory(),
                        expenseRequest.getReason(),
                        expenseRequest.getSatisfaction()
                );

                diary.addExpense(expense);
            }
        }

        Diary savedDiary = diaryRepository.save(diary);

        return new DiaryCreateResponse(
                savedDiary.getDiaryDate(),
                savedDiary.getTotalExpense()
        );
    }

    @Transactional(readOnly = true)
    public DiaryGetResponse getDiary(LocalDate diaryDate) {
        Diary diary = diaryRepository.findByDiaryDate(diaryDate)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 일기가 없습니다."));

        return DiaryGetResponse.from(diary);
    }

    public DiaryCreateResponse updateDiary(DiaryCreateRequest request) {
        Diary diary = diaryRepository.findByDiaryDate(request.getDiary_date())
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 일기가 없습니다."));

        diary.update(
                request.getEmotion().getEmoji(),
                request.getEmotion().getReason(),
                request.getEmotion().getTags(),
                request.getEmotion().getMemo()
        );

        diary.clearExpenses();

        if (request.getExpenses() != null) {
            for (ExpenseRequest expenseRequest : request.getExpenses()) {
                Expense expense = new Expense(
                        expenseRequest.getAmount(),
                        expenseRequest.getCategory(),
                        expenseRequest.getReason(),
                        expenseRequest.getSatisfaction()
                );

                diary.addExpense(expense);
            }
        }

        return new DiaryCreateResponse(
                diary.getDiaryDate(),
                diary.getTotalExpense()
        );
    }

    public ExpenseDeleteResponse deleteExpense(LocalDate diaryDate, Long expenseId) {
        Diary diary = diaryRepository.findByDiaryDate(diaryDate)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 일기가 없습니다."));

        boolean exists = diary.getExpenses().stream()
                .anyMatch(expense -> expense.getId().equals(expenseId));

        if (!exists) {
            throw new IllegalArgumentException("해당 소비 항목이 없습니다.");
        }

        diary.removeExpense(expenseId);

        return new ExpenseDeleteResponse(diary.getDiaryDate(), expenseId);
    }

    public DiaryDeleteResponse deleteDiary(LocalDate diaryDate) {
        Diary diary = diaryRepository.findByDiaryDate(diaryDate)
                .orElseThrow(() -> new IllegalArgumentException("해당 날짜의 일기가 없습니다."));

        diaryRepository.delete(diary);

        return new DiaryDeleteResponse(diaryDate);
    }
}
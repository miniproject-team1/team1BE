package com.miniproject.be.domain.diary.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.diary.dto.*;
import com.miniproject.be.domain.diary.entity.Diary;
import com.miniproject.be.domain.diary.entity.Expense;
import com.miniproject.be.domain.diary.repository.DiaryRepository;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public DiaryCreateResponse createDiary(Long userId, DiaryCreateRequest request) {

        if (diaryRepository.findByUserIdAndDiaryDate(userId, request.getDiary_date()).isPresent()) {
            throw new CustomException(ErrorCode.DIARY_ALREADY_EXISTS);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Diary diary = new Diary(
                user,
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
    public DiaryGetResponse getDiary(Long userId, LocalDate diaryDate) {
        Diary diary = findOwnedDiary(userId, diaryDate);

        return DiaryGetResponse.from(diary);
    }

    public DiaryCreateResponse updateDiary(Long userId, DiaryCreateRequest request) {
        Diary diary = findOwnedDiary(userId, request.getDiary_date());

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

    public ExpenseDeleteResponse deleteExpense(Long userId, LocalDate diaryDate, Long expenseId) {
        Diary diary = findOwnedDiary(userId, diaryDate);

        boolean exists = diary.getExpenses().stream()
                .anyMatch(expense -> expense.getId().equals(expenseId));

        if (!exists) {
            throw new CustomException(ErrorCode.EXPENSE_NOT_FOUND);
        }

        diary.removeExpense(expenseId);

        return new ExpenseDeleteResponse(diary.getDiaryDate(), expenseId);
    }

    public DiaryDeleteResponse deleteDiary(Long userId, LocalDate diaryDate) {
        Diary diary = findOwnedDiary(userId, diaryDate);

        diaryRepository.delete(diary);

        return new DiaryDeleteResponse(diaryDate);
    }

    // 일기를 찾고, 그게 이 유저의 것인지 확인하는 공통 메서드
    private Diary findOwnedDiary(Long userId, LocalDate diaryDate) {
        return diaryRepository.findByUserIdAndDiaryDate(userId, diaryDate)
                .orElseThrow(() -> new CustomException(ErrorCode.DIARY_NOT_FOUND));
    }
}
package com.miniproject.be.domain.mypage.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.mypage.dto.response.BudgetResponse;
import com.miniproject.be.domain.mypage.entity.Budget;
import com.miniproject.be.domain.mypage.repository.BudgetRepository;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;


    public BudgetResponse getBudget(Long userId, int year, int month) {

        int amount = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, year, month)
                .map(Budget::getAmount)
                .orElse(0);

        return new BudgetResponse(year, month, amount);
    }


    @Transactional
    public BudgetResponse updateBudget(Long userId, int year, int month, int budgetAmount) {

        Budget budget = budgetRepository
                .findByUser_IdAndYearAndMonth(userId, year, month)
                .orElse(null);

        if (budget == null) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
            budget = new Budget(user, year, month, budgetAmount);
            budgetRepository.save(budget);
        } else {
            budget.changeAmount(budgetAmount);
        }

        return new BudgetResponse(year, month, budget.getAmount());
    }


    public Object getAnalyticsSummary(Long userId, String period) {
        return null;
    }
}
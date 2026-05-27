package com.miniproject.be.domain.mypage.repository;

import com.miniproject.be.domain.mypage.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Optional<Budget> findByUser_IdAndYearAndMonth(Long userId, int year, int month);
}
package com.miniproject.be.domain.diary.repository;

import com.miniproject.be.domain.diary.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // 특정 유저의, 특정 기간(다이어리 날짜 기준) 소비 전체 조회
    @Query("""
            select e
            from Expense e
            join e.diary d
            where d.user.id = :userId
              and d.diaryDate between :startDate and :endDate
            """)
    List<Expense> findAllByUserAndPeriod(@Param("userId") Long userId,
                                         @Param("startDate") LocalDate startDate,
                                         @Param("endDate") LocalDate endDate);
}
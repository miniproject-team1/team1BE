package com.miniproject.be.domain.diary.repository;

import com.miniproject.be.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    @Query("""
            select distinct d
            from Diary d
            left join fetch d.expenses
            where d.user.id = :userId
              and d.diaryDate = :diaryDate
            """)
    Optional<Diary> findByUserIdAndDiaryDate(@Param("userId") Long userId,
                                             @Param("diaryDate") LocalDate diaryDate);
}
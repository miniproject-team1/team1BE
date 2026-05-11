package com.miniproject.be.domain.diary.repository;

import com.miniproject.be.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DiaryRepository extends JpaRepository<Diary, Long> {

}
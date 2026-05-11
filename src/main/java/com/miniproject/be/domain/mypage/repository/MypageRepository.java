package com.miniproject.be.domain.mypage.repository;

import com.miniproject.be.domain.mypage.entity.Mypage;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MypageRepository extends JpaRepository<Mypage, Long> {

}
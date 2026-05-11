package com.miniproject.be.domain.mypage.service;

import com.miniproject.be.domain.mypage.repository.MypageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageService {

    private final MypageRepository mypageRepository;

}
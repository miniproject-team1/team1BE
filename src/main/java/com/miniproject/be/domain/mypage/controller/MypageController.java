package com.miniproject.be.domain.mypage.controller;

import com.miniproject.be.domain.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

}
package com.miniproject.be.domain.user.service;

import com.miniproject.be.common.exception.CustomException;
import com.miniproject.be.common.exception.ErrorCode;
import com.miniproject.be.domain.user.dto.NicknameResponse;
import com.miniproject.be.domain.user.dto.NicknameUpdateRequest;
import com.miniproject.be.domain.user.entity.User;
import com.miniproject.be.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public NicknameResponse updateNickname(Long userId, NicknameUpdateRequest req) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        if (userRepository.existsByNickname(req.getNickname())) {
            throw new CustomException(ErrorCode.DUPLICATE_NICKNAME);
        }

        user.changeNickname(req.getNickname());

        return NicknameResponse.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
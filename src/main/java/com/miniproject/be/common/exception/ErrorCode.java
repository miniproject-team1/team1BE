package com.miniproject.be.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "유효하지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "허용되지 않은 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 내부 오류가 발생했습니다."),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "C004", "요청한 리소스를 찾을 수 없습니다."),

    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "A001", "인증이 필요합니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A002", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "만료된 토큰입니다. 다시 로그인해주세요."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "A004", "접근 권한이 없습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "U001", "사용자를 찾을 수 없습니다."),
    DUPLICATE_LOGIN_ID(HttpStatus.CONFLICT, "U002", "이미 사용 중인 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "U003", "이미 사용 중인 이메일입니다."),
    DUPLICATE_NICKNAME(HttpStatus.CONFLICT, "U004", "이미 사용 중인 닉네임입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "U005", "아이디 또는 비밀번호가 올바르지 않습니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "U006", "비밀번호가 일치하지 않습니다."),

    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "D001", "해당 날짜의 일기를 찾을 수 없습니다."),
    DIARY_ALREADY_EXISTS(HttpStatus.CONFLICT, "D002", "해당 날짜에 이미 일기가 존재합니다."),
    DIARY_ACCESS_DENIED(HttpStatus.FORBIDDEN, "D003", "해당 일기에 접근할 권한이 없습니다."),
    EXPENSE_NOT_FOUND(HttpStatus.NOT_FOUND, "D004", "해당 소비 항목을 찾을 수 없습니다."),

    BUDGET_NOT_FOUND(HttpStatus.NOT_FOUND, "M001", "예산이 설정되지 않았습니다."),
    INVALID_BUDGET_AMOUNT(HttpStatus.BAD_REQUEST, "M002", "유효하지 않은 예산 금액입니다."),
    INVALID_PERIOD(HttpStatus.BAD_REQUEST, "M003", "유효하지 않은 조회 기간입니다."),

    WISHLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "W001", "위시리스트 항목을 찾을 수 없습니다."),
    WISHLIST_ACCESS_DENIED(HttpStatus.FORBIDDEN, "W002", "해당 위시리스트에 접근할 권한이 없습니다."),

    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "N001", "알림 설정을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
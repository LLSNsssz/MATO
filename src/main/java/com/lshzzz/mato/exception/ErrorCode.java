package com.lshzzz.mato.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "INVALID_PASSWORD", "비밀번호가 일치하지 않습니다."),
    ID_ALREADY_EXISTS(HttpStatus.CONFLICT, "ID_ALREADY_EXISTS", "이미 사용 중인 아이디입니다."),
    INVALID_PASSWORD_CONFIRMATION(HttpStatus.BAD_REQUEST, "INVALID_PASSWORD_CONFIRMATION", "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED", "로그인이 필요합니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "INVALID_REQUEST", "요청이 유효하지 않습니다."),
    USER_ALREADY_DELETED(HttpStatus.BAD_REQUEST, "USER_ALREADY_DELETED", "이미 탈퇴한 사용자입니다.");

    private final HttpStatus status;  // HTTP 상태 코드
    private final String code;    // 에러 코드
    private final String message; // 에러 메시지
}
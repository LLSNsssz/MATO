package com.lshzzz.mato.exception;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 커스텀 예외 처리
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException e) {
        return ResponseEntity.status(e.getStatus())
            .body(Map.of("errorCode", e.getErrorCode().getCode(),
                "message", e.getErrorCode().getMessage()
            ));
    }

    // 일반적인 예외 처리
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception e) {
        return ResponseEntity.internalServerError().body(Map.of(
            "errorCode", "INTERNAL_SERVER_ERROR",
            "message", e.getMessage()
        ));
    }

    // 유효성 검증 실패 예외 처리
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
        MethodArgumentNotValidException e) {
        String errors = e.getBindingResult().getFieldErrors().stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(Map.of(
            "errorCode", "VALIDATION_FAILED",
            "message", errors
        ));
    }
}
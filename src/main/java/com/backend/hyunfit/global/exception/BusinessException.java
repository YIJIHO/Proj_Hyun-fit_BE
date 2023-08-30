package com.backend.hyunfit.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public static BusinessException of(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }
}
package com.example.concert.interfaces.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_EXIST("E103", HttpStatus.BAD_REQUEST,"유저가 존재하지 않습니다.");
    private final String code;
    private final HttpStatus statusCode;
    private final String message;

}

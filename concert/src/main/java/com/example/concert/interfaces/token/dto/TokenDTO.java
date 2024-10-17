package com.example.concert.interfaces.token.dto;

import com.example.concert.domain.token.entity.WaitStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class TokenDTO {


    public record CreateTokenAccess(
            @NotEmpty @Valid
            @Schema(name = "userId", description ="유저 아이디", example = "1")
            Long userId
    ){
    }

    public record TokenAccessResponse(
            @Schema(name = "userId", description = "유저 아이디", example = "1")
            Long userId,
            @Schema(name = "waitStatus", description = "대기열 상태값 - 접근권한", example = "ACCESS")
            WaitStatus waitStatus,
            @Schema(name = "token", description = "접근권한 토큰", example = "550e8400-e29b-41d4-a716-446655440000")
            String token


    ) {
    }

    public record CreateTokenWaiting(
            @NotEmpty @Valid
            @Schema(name = "userId", description ="유저 아이디", example = "1")
            Long userId,
            @NotEmpty @Valid
            @Schema(name = "concertId", description ="콘서트 아이디", example = "1")
            Long concertId
    ){
    }

    public record TokenWaitingResponse(
            @Schema(name = "userId", description = "유저 아이디", example = "1")
            Long userId,
            @Schema(name = "waitStatus", description = "대기열 상태값 - 대기권한", example = "WAITING")
            WaitStatus waitStatus,
            @Schema(name = "token", description = "대기권한 토큰", example = "550e8400-e29b-41d4-a716-446655440001")
            String token,
            @Schema(name = "expireTime", description = "대기권한 만료시간", example = "2021-08-01T12:34:56")
            LocalDateTime expireTime



    ) {
    }

    public record CheckUserOrder(
            @NotEmpty @Valid
            @Schema(name = "userId", description ="유저 아이디", example = "1")
            Long userId,
            @NotEmpty @Valid
            @Schema(name = "concertId", description ="콘서트 아이디", example = "1")
            Long concertId
    ){
    }
}

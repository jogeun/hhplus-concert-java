package com.example.concert.domain.token.entity;

public enum WaitStatus {
    ACCESS, //권한부여 상태
    WAITING,//대기 상태
    PROGRESS,//결재 진행 상태
    EXIPRED// 토큰 만료 상태
}

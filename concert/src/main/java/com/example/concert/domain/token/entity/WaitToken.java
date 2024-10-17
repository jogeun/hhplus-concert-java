package com.example.concert.domain.token.entity;

import com.example.concert.infrastructure.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "wait_token")
public class WaitToken extends BaseTime {

    //final int MAX_TIME = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "concert_id")
    private Long concertId;

    @Enumerated(EnumType.STRING)
    @Column(name = "wait_status")
    private WaitStatus waitStatus;

    private String token;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "expire_time")
    private LocalDateTime expireTime;
    @Builder
    public WaitToken(Long tokenId, Long userId, Long concertId, WaitStatus waitStatus) {
        this.id = tokenId;
        this.userId = userId;
        this.concertId = concertId;
        this.waitStatus = waitStatus;
        this.token = generateNewToken();
        if(waitStatus == WaitStatus.WAITING) {
            this.startTime = LocalDateTime.now();
            this.expireTime = LocalDateTime.now().plusMinutes(5);
        }else{
            this.startTime = null;
            this.expireTime = null;
        }
    }
    // 새로운 토큰 생성
    private static String generateNewToken() {
        return UUID.randomUUID().toString();
    }
}

package com.example.concert.infrastructure.token;

import com.example.concert.domain.token.entity.WaitStatus;
import com.example.concert.domain.token.entity.WaitToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public abstract class WaitQueueRepositoryImpl implements WaitQueueRepository {

    private final WaitQueueRepository repository;
    //토큰 유저 권한 조회(대기열 유저 확인)-userId 기반 + ACCESS ==> 접근 권한 가능한 유전인지 확인
    @Override
    public WaitToken findByUserIdAndWaitStatus(Long userId, WaitStatus waitStatus){
        return repository.findByUserIdAndWaitStatus(userId, waitStatus);
    }
    //대기순번 조회
    @Override
    public int findTokenOrder(Long userId, Long concertId){
        return repository.findTokenOrder(userId,concertId);
    }
}

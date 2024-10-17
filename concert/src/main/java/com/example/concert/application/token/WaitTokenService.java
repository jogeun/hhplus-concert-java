package com.example.concert.application.token;

import com.example.concert.domain.token.entity.WaitStatus;
import com.example.concert.infrastructure.token.WaitQueueRepository;
import com.example.concert.domain.token.entity.WaitToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WaitTokenService {

    private final WaitQueueRepository waitQueueRepository;

    //토큰 유저 권한 조회(대기열 유저 확인)-userId 기반 ==> 테스트 용
    public WaitToken selectTokenById(Long userId){
        return waitQueueRepository.findById(userId).orElseThrow();
    }

    //토큰 유저 권한 조회(대기열 유저 확인)-userId 기반 + ACCESS 상태 ==> 접근 권한 가능한 유전인지 확인
    public WaitToken selectTokenByUserId(Long userId){
          return waitQueueRepository.findByUserIdAndWaitStatus(userId, WaitStatus.ACCESS);
    }



    //토큰 권한 생성하기 [접근권한]
    public WaitToken createTokenByStatusAccess(Long userId){
        return waitQueueRepository.save(WaitToken.builder().userId(userId).waitStatus(WaitStatus.ACCESS).build());
    }

    //토큰 권한 생성하기 [대기권한]
    public WaitToken createTokenByStatusWait(Long userId, Long concertId){
        return waitQueueRepository.save(WaitToken.builder().userId(userId).concertId(concertId).waitStatus(WaitStatus.WAITING).build());
    }

    //토큰 상태값 변경하기
    @Transactional
    public void changeTokenByStatus(Long tokenId, WaitStatus waitStatus){
        WaitToken token = waitQueueRepository.findById(tokenId).orElseThrow(()-> new IllegalArgumentException("해당 토큰이 존재하지 않습니다."));

        WaitToken.builder().tokenId(tokenId).waitStatus(waitStatus).build();
    }

    //대기순서 조회하기
    public int findTokenOrder(Long tokenId, Long concertId){
        return waitQueueRepository.findTokenOrder(tokenId,concertId);
    }

}

package com.example.concert.unit.token;

import com.example.concert.application.token.WaitTokenService;
import com.example.concert.domain.token.entity.WaitStatus;
import com.example.concert.infrastructure.token.WaitQueueRepository;
import com.example.concert.domain.token.entity.WaitToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TokenServiceUnitTest {

    @InjectMocks
    WaitTokenService sut;

    @Mock
    WaitQueueRepository waitQueueRepository;

    @DisplayName("[성공 POST] 사용자 아이디로 토큰 ACCESS 상태일시 >>> 접근 권한 발급")
    @Test
    void postNewTokenAccessByUserId(){
        //given
        Long tokenId = 100L;
        Long userId = 100L;
        Long concertId = 100L;

        WaitToken token = WaitToken.builder().tokenId(tokenId).userId(userId).concertId(concertId).waitStatus(WaitStatus.ACCESS).build();

        waitQueueRepository.save(token);

        when(waitQueueRepository.findById(tokenId))
                .thenReturn(Optional.of(token));
        //when
        WaitToken result = sut.selectTokenById(tokenId);

        //then
        assertThat(result).isEqualTo(token);
        assertThat(result.getWaitStatus()).isEqualTo(WaitStatus.ACCESS);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getId()).isEqualTo(tokenId);
        assertThat(result.getConcertId()).isNull();// 신규발급일땐 콘서트 id 존재
        assertThat(result.getStartTime()).isNull();// 신규발급일땐 시작일자 존재 x
        assertThat(result.getExpireTime()).isNull();// 신규발급일땐 종료일자 존재 x
    }

    @DisplayName("[성공 POST] 사용자 아이디로 토큰 WAITING 상태일시 >>> 대기 권한 발급")
    @Test
    void postNewTokenWaitingByUserId(){
        //given
        Long tokenId = 100L;
        Long userId = 100L;
        Long concertId = 100L;

        WaitToken token = WaitToken.builder().tokenId(tokenId).userId(userId).concertId(concertId).waitStatus(WaitStatus.WAITING).build();

        waitQueueRepository.save(token);

        when(waitQueueRepository.findById(tokenId))
                .thenReturn(Optional.of(token));
        //when
        WaitToken result = sut.selectTokenById(tokenId);

        //then
        assertThat(result).isEqualTo(token);
        assertThat(result.getWaitStatus()).isEqualTo(WaitStatus.WAITING);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getId()).isEqualTo(tokenId);
        assertThat(result.getConcertId()).isEqualTo(concertId);
        assertThat(result.getStartTime()).isNotNull();
        assertThat(result.getExpireTime()).isNotNull();
    }

    @DisplayName("[성공 GET] userId 기반 + ACCESS 상태 ==> 접근 권한 가능한 유전인지 확인")
    @Test
    void getTokenByUserId(){
        //given
        Long tokenId = 52L;
        Long userId = 5L;
        Long concertId = 32L;

        WaitToken token = WaitToken.builder().tokenId(tokenId).userId(userId).concertId(concertId).waitStatus(WaitStatus.ACCESS).build();

        waitQueueRepository.save(token);

        when(waitQueueRepository.findByUserIdAndWaitStatus(userId, WaitStatus.ACCESS))
                .thenReturn(token);
        //when
        WaitToken result = sut.selectTokenByUserId(userId);

        //then
        assertThat(result).isEqualTo(token);
        assertThat(result.getWaitStatus()).isEqualTo(WaitStatus.ACCESS);
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getId()).isEqualTo(tokenId);
        assertThat(result.getConcertId()).isEqualTo(concertId);
    }




    @DisplayName("[성공 GET] 사용자 아이디와 콘서트 아이디로 대기 순서 조회")
    @Test
    void getTokenOrderByUserId() {

        long userId = 2L;
        long concertId = 2L;
        //given
        when(waitQueueRepository.findTokenOrder(userId, concertId))
                .thenReturn(3);
        //when
        int result = sut.findTokenOrder(userId,concertId);

        //then
        assertThat(result).isEqualTo(3);

    }


}

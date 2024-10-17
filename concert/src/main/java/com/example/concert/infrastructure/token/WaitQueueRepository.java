package com.example.concert.infrastructure.token;

import com.example.concert.domain.token.entity.WaitStatus;
import com.example.concert.domain.token.entity.WaitToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WaitQueueRepository extends JpaRepository<WaitToken,Long> {

    //토큰 저장

    //토큰 조회

    //토큰 유저 권한 조회(대기열 유저 확인)-userId 기반 + ACCESS ==> 접근 권한 가능한 유전인지 확인
    WaitToken findByUserIdAndWaitStatus(Long userId, WaitStatus waitStatus);

    //대기순번 조회
    @Query(nativeQuery = true, value =
            "select user_id, concert_id, user_order " +
                    "from ( " +
                    "select a.user_id,a.concert_id,row_number() over (order by a.start_time) as user_order From wait_token a where  a.wait_status = 'WAITING' " +
                    ") token_order_table " +
                    "where user_id = :userId " +
                    "and concert_id = :concertId limit  1"
    )
    int findTokenOrder(@Param("userId") Long userId, @Param("concertId") Long concertId);

}

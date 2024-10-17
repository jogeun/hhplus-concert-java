package com.example.concert.infrastructure.reservation;

import com.example.concert.infrastructure.token.WaitQueueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public abstract class ReservationRepositoryImpl implements ReservationRepository {

    private final ReservationRepository repository;
}

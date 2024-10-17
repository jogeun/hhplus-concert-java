package com.example.concert.application.reservation;

import com.example.concert.domain.reservation.entity.Reservation;
import com.example.concert.infrastructure.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    //예약생성하기
    public Reservation createReservation(Long paymentId){
        return reservationRepository.save(Reservation.builder().paymentId(paymentId).build());
    }
}

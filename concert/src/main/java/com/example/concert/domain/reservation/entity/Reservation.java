package com.example.concert.domain.reservation.entity;

import com.example.concert.domain.token.entity.WaitStatus;
import com.example.concert.infrastructure.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Reservation extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long id;

    @Column(name = "payment_id", nullable = false)
    private Long paymentId;

    @Column(name = "reserve_yn")
    private Character reserveYn;

    @Builder
    public Reservation(Long reserveId, Long paymentId, Character reserveYn) {
        this.id = reserveId;
        this.paymentId = paymentId;
        this.reserveYn = reserveYn;

    }

}

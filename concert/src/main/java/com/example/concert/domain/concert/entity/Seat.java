package com.example.concert.domain.concert.entity;

import com.example.concert.infrastructure.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Seat extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long id;

    @Column(name = "concert_dt_id" , nullable = false)
    private Long concertDtId;

    @Column(name = "seat_row")
    private Integer  seatRow;

    @Column(name = "temp_yn")
    private Character tempYn;

    @Column(name = "seat_price")
    private Integer seatPrice;

}

package com.example.concert.domain.concert.entity;

import com.example.concert.infrastructure.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "concert_dt")
public class ConcertDt extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "concert_dt_id")
    private Long id;

    @Column(name = "concert_id" , nullable = false)
    private Long concertId;

    @Column(name = "concert_time")
    private String  concertTime;

    @Column(name = "remain_seat")
    private Integer remainSeat;

    @Column(name = "concert_price")
    private Integer concertPrice;

}

package com.example.concert.infrastructure.concert;

import com.example.concert.domain.concert.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
}

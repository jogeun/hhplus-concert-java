package com.example.concert.infrastructure.concert;

import com.example.concert.domain.concert.entity.ConcertDt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDtRepository extends JpaRepository<ConcertDt,Long> {
}

package com.example.concert.infrastructure.payment;

import com.example.concert.domain.concert.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Seat,Long> {
}

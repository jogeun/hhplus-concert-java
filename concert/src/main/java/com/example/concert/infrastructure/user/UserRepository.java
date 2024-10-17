package com.example.concert.infrastructure.user;

import com.example.concert.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

   //유저 저장
   //유저이름으로 찾기
   User findByName(String name);
}

package com.example.concert.application.user;

import com.example.concert.infrastructure.user.UserRepository;
import com.example.concert.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    //유저 정보 이름으로 조회
    public User selectByUserName(String name){
        return userRepository.findByName(name);
    }
}

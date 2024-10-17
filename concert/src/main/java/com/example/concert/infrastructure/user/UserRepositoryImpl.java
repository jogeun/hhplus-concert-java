package com.example.concert.infrastructure.user;

import com.example.concert.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Repository
public abstract class UserRepositoryImpl implements UserRepository {

    private final UserRepository repository;
    @Override
    public User findByName(String name){
        return repository.findByName(name);
    }

}

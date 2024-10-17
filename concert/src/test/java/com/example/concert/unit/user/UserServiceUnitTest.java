package com.example.concert.unit.user;

import com.example.concert.application.user.UserService;
import com.example.concert.infrastructure.user.UserRepository;
import com.example.concert.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {


    @InjectMocks
    UserService sut;

    @Mock
    UserRepository userRepository;

    @DisplayName("[GET] 사용자 이름으로 유저 조회")
    @Test
    void getUserByUserName(){

        //given
        Long userId = 1L;
        String userName = "test";

        User user = User.builder().userId(userId).userName(userName).build();

        userRepository.save(user);

        when(userRepository.findByName(userName))
                .thenReturn(user);
        //when
        User result = sut.selectByUserName(userName);

        //then
        assertThat(result).isEqualTo(user);
        assertThat(result.getName()).isEqualTo(userName);
        assertThat(result.getId()).isEqualTo(userId);
        assertThat(result.getPoint()).isEqualTo(0);
    }
}

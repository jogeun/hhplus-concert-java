package com.example.concert.domain.user.entity;

import com.example.concert.infrastructure.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private Integer point;

    //@Column(name = "remain_time")
    //private LocalDateTime remainTime;

    @Builder
    public User( Long userId, String userName) {
        this.id = userId;
        this.name = userName;
        this.point = 0;
       // this.remainTime  = null;
    }

}

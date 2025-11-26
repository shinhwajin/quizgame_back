package com.mini.quizgame.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private String userId;
    private String userName;
    private String password;
    private LocalDateTime joinedAt;

    public UserDto(String userId, String userName, String password, LocalDateTime joinedAt) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.joinedAt = joinedAt;
    }
}

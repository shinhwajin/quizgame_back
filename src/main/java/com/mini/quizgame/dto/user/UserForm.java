package com.mini.quizgame.dto.user;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {

    private String userId;
    @NotBlank
    private String userName;
    private String password;
    private LocalDateTime joinedAt;
}

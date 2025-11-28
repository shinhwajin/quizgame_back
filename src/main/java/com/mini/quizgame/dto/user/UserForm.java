package com.mini.quizgame.dto.user;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserForm {

    private Long userId;

    @NotBlank
    private String userName;

    @NotBlank
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,12}$",
            message = "비밀번호는 영문+숫자 조합 8~12자여야 합니다."
    )
    private String password;

    private LocalDateTime joinedAt;
}

package com.mini.quizgame.service.user;

import com.mini.quizgame.common.ErrorCode;
import com.mini.quizgame.domain.User;
import com.mini.quizgame.common.GlobalException;
import com.mini.quizgame.dto.user.UserForm;
import com.mini.quizgame.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public Long createUser(UserForm form) {

        if (userRepository.existsByUserName(form.getUserName())) {
            throw new GlobalException(ErrorCode.DUPLICATE_USER);
        }

        User user = userRepository.save(
                User.builder()
                        .userName(form.getUserName())
                        .password(passwordEncoder.encode(form.getPassword()))
                        .joinedAt(LocalDateTime.now())
                        .build()
        );
        return user.getId();
    }

}

package com.mini.quizgame.service.user;

import com.mini.quizgame.common.ErrorCode;
import com.mini.quizgame.common.GlobalException;
import com.mini.quizgame.domain.User;
import com.mini.quizgame.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인
     */
    public User login(String userName, String password) {

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new GlobalException(ErrorCode.USER_NOT_FOUND)
        );
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new GlobalException(ErrorCode.INVALID_PASSWORD);
        }
        return user;
    }

}

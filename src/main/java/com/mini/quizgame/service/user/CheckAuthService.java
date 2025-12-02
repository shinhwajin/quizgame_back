package com.mini.quizgame.service.user;

import com.mini.quizgame.common.ErrorCode;
import com.mini.quizgame.common.GlobalException;
import com.mini.quizgame.domain.User;
import com.mini.quizgame.repository.user.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CheckAuthService {

    private final UserRepository userRepository;

    /**
     * 권한체크
     */
    public Long checkAuth(HttpSession session) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER");
        if (loginUserId == null) {
            throw new GlobalException(ErrorCode.UNAUTHORIZED);
        }

        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        return user.getId();
    }

}

package com.mini.quizgame.service.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LogoutService {

    /**
     * 로그아웃
     */
    public void logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", "")
                .path("/")
                .maxAge(0)      // 즉시 만료
                .httpOnly(true)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
    }

}

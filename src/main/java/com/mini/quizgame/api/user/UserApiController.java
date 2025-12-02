package com.mini.quizgame.api.user;

import com.mini.quizgame.common.ApiResponse;
import com.mini.quizgame.common.ErrorCode;
import com.mini.quizgame.common.GlobalException;
import com.mini.quizgame.domain.User;
import com.mini.quizgame.dto.user.LoginForm;
import com.mini.quizgame.dto.user.UserForm;
import com.mini.quizgame.repository.user.UserRepository;
import com.mini.quizgame.service.user.CreateUserService;
import com.mini.quizgame.service.user.LoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final CreateUserService createUserService;
    private final UserRepository userRepository;
    private final LoginService loginService;

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody @Valid UserForm form) {
        return ResponseEntity.ok(ApiResponse.success(createUserService.createUser(form), "회원가입 성공"));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginForm form, HttpSession session) {

        User loginUser = loginService.login(form.getUserName(), form.getPassword());
        session.setAttribute("LOGIN_USER", loginUser.getId());

        return ResponseEntity.ok(ApiResponse.success(loginUser.getId(), "로그인 성공"));
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<?>> logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", "")
                .path("/")
                .maxAge(0)      // 즉시 만료
                .httpOnly(true)
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
        return ResponseEntity.ok(ApiResponse.success(null, "로그아웃 성공"));
    }

    /**
     * 권한 체크
     */
    @GetMapping("/check-auth")
    public ResponseEntity<ApiResponse<?>> checkAuth(HttpSession session) {
        Long loginUserId = (Long) session.getAttribute("LOGIN_USER");
        if (loginUserId == null) {
            throw new GlobalException(ErrorCode.UNAUTHORIZED);
        }

        User user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new GlobalException(ErrorCode.USER_NOT_FOUND));

        return ResponseEntity.ok(ApiResponse.success(user.getId(), "권한 있음"));
    }
}

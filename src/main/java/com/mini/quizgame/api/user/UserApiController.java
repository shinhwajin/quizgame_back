package com.mini.quizgame.api.user;

import com.mini.quizgame.common.ApiResponse;
import com.mini.quizgame.common.ErrorCode;
import com.mini.quizgame.common.GlobalException;
import com.mini.quizgame.domain.User;
import com.mini.quizgame.dto.user.LoginForm;
import com.mini.quizgame.dto.user.UserForm;
import com.mini.quizgame.service.user.CreateUserService;
import com.mini.quizgame.service.user.LoginService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApiController {

    private final CreateUserService createUserService;
    private final LoginService loginService;

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody @Valid UserForm form) {
        return ResponseEntity.ok(ApiResponse.success(createUserService.createUser(form)));
    }

    /**
     * 로그인
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(@RequestBody @Valid LoginForm form, HttpSession session) {

        User loginUser = loginService.login(form.getUserName(), form.getPassword());
        session.setAttribute("LOGIN_USER", loginUser.getId());

        return ResponseEntity.ok(ApiResponse.success("로그인 성공"));
    }

    @GetMapping("/auth")
    public ResponseEntity<String> main(HttpSession session) {

        Long loginUserId = (Long) session.getAttribute("LOGIN_USER");

        if (loginUserId == null) {
            throw new GlobalException(ErrorCode.UNAUTHORIZED);
        }

        return ResponseEntity.ok("마이페이지 조회 성공");
    }

}

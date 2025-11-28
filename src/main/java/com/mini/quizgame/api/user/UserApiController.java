package com.mini.quizgame.api.user;

import com.mini.quizgame.common.ApiResponse;
import com.mini.quizgame.dto.user.UserForm;
import com.mini.quizgame.service.user.CreateUserService;
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

    /**
     * 회원가입
     */
    @PostMapping("/join")
    public ResponseEntity<ApiResponse<?>> createUser(@RequestBody @Valid UserForm form) {
        return ResponseEntity.ok(ApiResponse.success(createUserService.createUser(form)));
    }

}

package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.dto.JwtToken;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.mapper.UserMapper;
import io.github.imtotem.shortly.service.user.login.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/login")
public class LoginController {

    private final LoginService service;

    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<JwtToken> login(@RequestBody @Valid UserInfoRequest request) {
        JwtToken token = service.login(mapper.toUser(request));

        return ResponseEntity.ok(token);
    }
}

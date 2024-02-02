package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import io.github.imtotem.shortly.mapper.UserMapper;
import io.github.imtotem.shortly.service.user.signup.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/signup")
public class SignUpController {
    private final SignUpService service;

    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserInfoResponse> signUp(@RequestBody @Valid UserInfoRequest request) {

        User user = service.createUser(mapper.toUser(request));

        return ResponseEntity.ok(mapper.toResponse(user));
    }
}

package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import io.github.imtotem.shortly.dto.user.UserResponse;
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

    @PostMapping
    public ResponseEntity<UserResponse> signUp(@RequestBody @Valid UserInfoRequest request) {

        User user = service.createUser(
                User.builder()
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        UserResponse response = UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }
}

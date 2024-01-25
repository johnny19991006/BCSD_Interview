package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import io.github.imtotem.shortly.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    @PostAuthorize("@checker.isSelf(returnObject.body.email)")
    @GetMapping
    public ResponseEntity<UserInfoResponse> getUser(@RequestBody @Valid UserInfoRequest request) {
        User user = service.findUser(request.getEmail());

        UserInfoResponse response = UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@checker.isSelf(request.email)")
    @PutMapping
    public ResponseEntity<UserInfoResponse> updateUser(@RequestBody @Valid UserInfoRequest request) {

        User user = service.updateUser(
                User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build()
        );

        UserInfoResponse response = UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("@checker.isSelf(request.email)")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestBody @Valid UserInfoRequest request) {

        boolean status = service.deleteUser(
                User.builder()
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .build()
        );

        return ResponseEntity.ok(status);
    }
}

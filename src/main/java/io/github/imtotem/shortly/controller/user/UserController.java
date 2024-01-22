package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import io.github.imtotem.shortly.dto.user.UserResponse;
import io.github.imtotem.shortly.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/{id}")
public class UserController {
    private final UserService service;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@PathVariable long id) {

        User user = service.findUser(id);

        UserResponse response = UserInfoResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id, @RequestBody @Valid UserInfoRequest request) {

        User user = service.updateUser(
                User.builder()
                    .id(id)
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

    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@PathVariable long id, @RequestBody @Valid UserInfoRequest request) {

        boolean status = service.deleteUser(
                User.builder()
                        .id(id)
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .build()
        );

        return ResponseEntity.ok(status);
    }
}

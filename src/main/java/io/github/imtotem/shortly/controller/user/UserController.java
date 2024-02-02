package io.github.imtotem.shortly.controller.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.user.UserInfoRequest;
import io.github.imtotem.shortly.dto.user.UserInfoResponse;
import io.github.imtotem.shortly.mapper.UserMapper;
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

    private final UserMapper mapper;

    @PostAuthorize("@checker.isSelf(returnObject.body.email)")
    @GetMapping
    public ResponseEntity<UserInfoResponse> getUser(@RequestBody @Valid UserInfoRequest request) {
        User user = service.findUser(request.getEmail());

        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @PreAuthorize("@checker.isSelf(#request.email)")
    @PutMapping
    public ResponseEntity<UserInfoResponse> updateUser(@RequestBody @Valid UserInfoRequest request) {

        User user = service.updateUser(mapper.toUser(request));

        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @PreAuthorize("@checker.isSelf(#request.email)")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteUser(@RequestBody @Valid UserInfoRequest request) {

        boolean status = service.deleteUser(mapper.toUser(request));

        return ResponseEntity.ok(status);
    }
}

package io.github.imtotem.shortly.controller.url;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.domain.UserUrl;
import io.github.imtotem.shortly.dto.url.UrlRequest;
import io.github.imtotem.shortly.dto.url.UrlResponse;
import io.github.imtotem.shortly.mapper.UrlMapper;
import io.github.imtotem.shortly.service.url.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/urls")
public class UrlController {
    private final UrlService service;

    private final UrlMapper mapper;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UrlResponse>> findAll(@AuthenticationPrincipal User user) {
        List<UserUrl> userUrls = service.findAll(user.getEmail());

        return ResponseEntity.ok(
            userUrls.stream().map(mapper::toResponse).toList()
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {

        UserUrl userUrl = service.createUrl(mapper.toUserUrl(user, request));

        return ResponseEntity.ok(mapper.toResponse(userUrl));
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<UrlResponse> updateDeletable(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {

        UserUrl userUrl = service.updateUrl(mapper.toUserUrl(user, request));

        return ResponseEntity.ok(mapper.toResponse(userUrl));
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteShortUlr(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {
        return ResponseEntity.ok(
                service.deleteUrl(mapper.toUserUrl(user, request))
        );
    }
}

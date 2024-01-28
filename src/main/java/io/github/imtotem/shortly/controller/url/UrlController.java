package io.github.imtotem.shortly.controller.url;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.domain.UserUrl;
import io.github.imtotem.shortly.dto.url.UrlRequest;
import io.github.imtotem.shortly.dto.url.UrlResponse;
import io.github.imtotem.shortly.service.url.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/urls")
public class UrlController {
    private final UrlService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<UrlResponse>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(
            service.findAll(user.getEmail())
                    .stream()
                    .map(userUrl -> UrlResponse.builder()
                            .id(userUrl.getId())
                            .originUrl(userUrl.getUrl().getOriginUrl())
                            .shortUrl(userUrl.getUrl().getShortUrl())
                            .description(userUrl.getDescription())
                            .isDeletable(userUrl.isDeletable())
                            .createdAt(userUrl.getCreatedAt())
                            .build()
                    )
                    .collect(Collectors.toList())
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<UrlResponse> createShortUrl(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {

        UserUrl userUrl = service.createUrl(request.toEntity(user));
        return ResponseEntity.ok(
                UrlResponse.builder()
                        .id(userUrl.getId())
                        .originUrl(userUrl.getUrl().getOriginUrl())
                        .shortUrl(userUrl.getUrl().getShortUrl())
                        .description(userUrl.getDescription())
                        .isDeletable(userUrl.isDeletable())
                        .createdAt(userUrl.getCreatedAt())
                        .build()
        );
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public ResponseEntity<UrlResponse> updateDeletable(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {

        UserUrl userUrl = service.updateUrl(request.toEntity(user));
        return ResponseEntity.ok(
                UrlResponse.builder()
                        .id(userUrl.getId())
                        .originUrl(userUrl.getUrl().getOriginUrl())
                        .shortUrl(userUrl.getUrl().getShortUrl())
                        .description(userUrl.getDescription())
                        .isDeletable(userUrl.isDeletable())
                        .createdAt(userUrl.getCreatedAt())
                        .build()
        );
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteShortUlr(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UrlRequest request) {
        return ResponseEntity.ok(
                service.deleteUrl(request.toEntity(user))
        );
    }
}

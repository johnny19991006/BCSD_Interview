package io.github.imtotem.shortly.controller.admin;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.notice.NoticeRequest;
import io.github.imtotem.shortly.dto.notice.NoticeResponse;
import io.github.imtotem.shortly.service.notice.NoticeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/notice")
public class AdminNoticeController {

    private final NoticeService service;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<NoticeResponse> createNotice(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid NoticeRequest request) {

        Notice notice = service.createNotice(
                Notice.builder()
                        .user(user)
                        .title(request.getTitle())
                        .content(request.getContent())
                        .originUrl(request.getOriginUrl())
                        .description(request.getDescription())
                        .build()
        );

        return ResponseEntity.ok(
                NoticeResponse.builder()
                        .email(notice.getUser().getEmail())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .originUrl(notice.getOriginUrl())
                        .description(notice.getDescription())
                        .build()
        );
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Notice>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}

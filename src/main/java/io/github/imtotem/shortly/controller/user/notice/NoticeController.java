package io.github.imtotem.shortly.controller.user.notice;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.notice.NoticeRequest;
import io.github.imtotem.shortly.dto.notice.NoticeResponse;
import io.github.imtotem.shortly.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/notice")
public class NoticeController {
    private final NoticeService service;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> findAll(@AuthenticationPrincipal User user) {
        List<Notice> notices = service.findAllByUser(user);

        return ResponseEntity.ok(
                notices.stream().map(notice -> NoticeResponse.builder()
                        .email(notice.getUser().getEmail())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .originUrl(notice.getOriginUrl())
                        .description(notice.getDescription())
                        .build()
                ).toList()
        );
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteNotice(@RequestBody NoticeRequest request) {
        return ResponseEntity.ok(service.deleteNotice(request.getId()));
    }

}

package io.github.imtotem.shortly.controller.admin;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.notice.NoticeRequest;
import io.github.imtotem.shortly.dto.notice.NoticeResponse;
import io.github.imtotem.shortly.mapper.NoticeMapper;
import io.github.imtotem.shortly.service.notice.NoticeService;
import io.github.imtotem.shortly.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/notice")
public class AdminNoticeController {

    private final NoticeService noticeService;
    private final UserService userService;

    private final NoticeMapper mapper;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<NoticeResponse> createNotice(@RequestBody @Valid NoticeRequest request) {
        User user = userService.findUser(request.getEmail());

        Notice notice = mapper.toNotice(user, request);

        NoticeResponse response = mapper.toResponse(noticeService.createNotice(notice));

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<NoticeResponse>> findAll() {
        List<Notice> notices = noticeService.findAll();

        return ResponseEntity.ok(
                notices.stream().map(mapper::toResponse).toList()
        );
    }
}

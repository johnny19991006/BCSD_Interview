package io.github.imtotem.shortly.controller.admin;

import io.github.imtotem.shortly.domain.Url;
import io.github.imtotem.shortly.dto.admin.AdminRequest;
import io.github.imtotem.shortly.mapper.UrlMapper;
import io.github.imtotem.shortly.service.admin.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService service;

    private final UrlMapper mapper;

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Url>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PreAuthorize("isAuthenticated() and hasRole('ADMIN')")
    @DeleteMapping
    public ResponseEntity<Boolean> deleteUrl(@RequestBody @Valid AdminRequest request) {
        return ResponseEntity.ok(
                service.deleteUrl(mapper.toUrl(request))
        );
    }
}

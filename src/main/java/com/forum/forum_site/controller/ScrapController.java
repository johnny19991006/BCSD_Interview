package com.forum.forum_site.controller;

import com.forum.forum_site.dto.SimplePostInfo;
import com.forum.forum_site.service.ScrapService;
import com.forum.forum_site.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {
    private final ScrapService scrapService;
    private final UserService userService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertScrap(@PathVariable("postId") Integer postId) {
        scrapService.insertScrap(postId);
    }

    @DeleteMapping("/{postId}")
    public void deleteScrap(@PathVariable("postId") Integer postId) {
        scrapService.deleteScrap(postId);
    }

    @GetMapping("/myScraps")
    public ResponseEntity<List<SimplePostInfo>> getUserScraps() {
        return ResponseEntity.ok(userService.getUserScrapList());
    }
}

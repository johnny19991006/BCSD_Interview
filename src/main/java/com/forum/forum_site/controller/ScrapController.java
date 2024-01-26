package com.forum.forum_site.controller;

import com.forum.forum_site.service.ScrapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scrap")
public class ScrapController {
    private final ScrapService scrapService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertScrap(@PathVariable("postId") Integer postId) {
        scrapService.insertScrap(postId);
    }

    @DeleteMapping("/{postId}")
    public void deleteScrap(@PathVariable("postId") Integer postId) {
        scrapService.deleteScrap(postId);
    }

}

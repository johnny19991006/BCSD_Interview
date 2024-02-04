package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import com.example.board.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hashtags")
public class HashtagController {
    private final HashtagService hashtagService;
    @Autowired
    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }
    @PostMapping
    public ResponseEntity<Hashtag> insertHashtag(@RequestBody Hashtag hashtag) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hashtagService.insertHashtag(hashtag));
    }
    @GetMapping
    public ResponseEntity<List<Hashtag>> getAllHashtags() {
        return ResponseEntity.ok().body(hashtagService.getAllHashtags());
    }
    @GetMapping("/{hashtagId}/boards")
    public ResponseEntity<List<Board>> getBoardsByHashtag(@PathVariable int hashtagId) {
        return ResponseEntity.ok().body(hashtagService.getBoardsByHashtagId(hashtagId));
    }
    @DeleteMapping("/{hashtagId}")
    public ResponseEntity<Void> deleteHashtag(@PathVariable Integer hashtagId) {
        hashtagService.deleteHashtag(hashtagId);
        return ResponseEntity.noContent().build();
    }
}
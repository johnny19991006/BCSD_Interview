package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import com.example.board.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    public Hashtag insertHashtag(@RequestBody Hashtag hashtag) throws SQLException {
        return hashtagService.insertHashtag(hashtag);
    }
    @GetMapping
    public List<Hashtag> getAllHashtags() throws SQLException {
        return hashtagService.getAllHashtags();
    }
    @DeleteMapping("/{hashtagId}")
    public void deleteHashtag(@PathVariable Integer hashtagId) throws SQLException {
        hashtagService.deleteHashtag(hashtagId);
    }
    @GetMapping("/{hashtagId}/boards")
    public List<Board> getBoardsByHashtag(@PathVariable int hashtagId) throws SQLException{
        return hashtagService.getBoardsByHashtagId(hashtagId);
    }
}

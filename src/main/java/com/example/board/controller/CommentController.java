package com.example.board.controller;

import com.example.board.domain.Comment;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment insertComment(@RequestBody Comment comment) throws SQLException {
        return commentService.insertComment(comment);
    }

    @PutMapping("/{commentId}")
    public void updateCommentContent(@PathVariable Integer commentId, @RequestBody String newContent) throws SQLException {
        commentService.updateCommentContent(commentId, newContent);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) throws SQLException {
        commentService.deleteComment(commentId);
    }
}

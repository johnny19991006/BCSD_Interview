package com.example.board.controller;

import com.example.board.domain.Comment;
import com.example.board.security.AuthorizeComment;
import com.example.board.security.AuthorizeUser;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

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
    @AuthorizeComment
    @PutMapping("/{commentId}")
    public void updateCommentContent(@PathVariable Integer commentId, @RequestBody String newContent) throws SQLException {
        commentService.updateCommentContent(commentId, newContent);
    }
    @AuthorizeComment
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Integer commentId) throws SQLException {
        commentService.deleteComment(commentId);
    }
    @AuthorizeUser
    @GetMapping("/user/{userId}")
    public List<Comment> getCommentByUserId(@PathVariable int userId) throws SQLException {
        return commentService.getCommentByUserId(userId);
    }
}

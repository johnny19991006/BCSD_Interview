package com.example.board.controller;

import com.example.board.dto.CommentRequestDTO;
import com.example.board.dto.CommentResponseDTO;
import com.example.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CommentResponseDTO> insertComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.insertComment(commentRequestDTO));
    }
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDTO> updateCommentContent(@PathVariable Integer commentId, @RequestBody String newContent) {
        return ResponseEntity.ok(commentService.updateCommentContent(commentId, newContent));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponseDTO>> getCommentByUserId(@PathVariable int userId) {
        return ResponseEntity.ok().body(commentService.getCommentByUserId(userId));
    }
}

package com.forum.forum_site.controller;

import com.forum.forum_site.dto.SaveCommentDto;
import com.forum.forum_site.dto.UpdateCommentDto;
import com.forum.forum_site.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveComment(@PathVariable("postId") Integer postId, SaveCommentDto saveCommentDto) {
        commentService.saveComment(postId, saveCommentDto);
    }

    @PostMapping("/{postId}/{commentId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRecomment(@PathVariable("postId") Integer postId,
                              @PathVariable("commentId") Integer commentId,
                              SaveCommentDto saveCommentDto) {
        commentService.saveReComment(postId, commentId, saveCommentDto);
    }

    @PutMapping("/{commentId}")
    public void updateComment(@PathVariable("commentId") Integer commentId,
                              UpdateCommentDto updateCommentDto) {
        commentService.updateComment(commentId, updateCommentDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable("commentId") Integer commentId) {
                              commentService.removeComment(commentId);
    }
}

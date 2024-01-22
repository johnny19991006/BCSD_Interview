package com.forum.forum_site.controller;

import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    // 글 게시
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void savePost(@Valid @ModelAttribute SavePostDto savePostDto) {
        postService.savePost(savePostDto);
    }

    // 글 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{postId}")
    public void updatePost(@PathVariable("postId") Integer postId, @ModelAttribute UpdatePostDto updatePostDto) {
        postService.updatePost(postId, updatePostDto);
    }

    // 글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable("postId") Integer postId) {
        postService.deletePost(postId);
    }
}

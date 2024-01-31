package com.forum.forum_site.controller;

import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.searchcond.SearchPostCondition;
import com.forum.forum_site.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
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
    // @valid -> 유효성 검사를 하는데 사용
    // @ModelAttribute -> 모든 요청에서 사용할 수 있는 모델 속성으로 됨
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

    // 글 조회
    @GetMapping("{postId}")
    public ResponseEntity getInfo (@PathVariable("postId") Integer postId) {
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    // 글 검색
    @GetMapping
    public ResponseEntity search(Pageable pageable,
                                 @ModelAttribute SearchPostCondition searchPostCondition) {
        return ResponseEntity.ok(postService.getPostList(pageable,searchPostCondition));
    }
}

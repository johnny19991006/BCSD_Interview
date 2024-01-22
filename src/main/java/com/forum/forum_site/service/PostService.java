package com.forum.forum_site.service;

import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;

import javax.annotation.processing.FilerException;

public interface PostService {
    // 글 게시
    void savePost(SavePostDto savePostDto);

    // 글 수정
    void updatePost(Integer id, UpdatePostDto updatePostDto);

    // 글 삭제
    void deletePost(Integer id);
}

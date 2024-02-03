package com.forum.forum_site.service;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.dto.PostInfoDto;
import com.forum.forum_site.dto.PostPagingDto;
import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.searchcond.SearchPostCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    // 글 게시
    void savePost(SavePostDto savePostDto);

    // 글 수정
    void updatePost(Integer id, UpdatePostDto updatePostDto);

    // 글 삭제
    void deletePost(Integer id);

    // id로 post 조회
    PostInfoDto getPostInfo(Integer id);

    // 게시글 검색
    PostPagingDto getPostList(Pageable pageable, SearchPostCondition searchPostCondition);

    List<PostInfoDto> getHotPosts(Pageable pageable);
}

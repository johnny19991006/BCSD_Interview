package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Post;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostPagingDto {
    private int totalPageCount; // 총 페이지
    private int currentPageNum; // 현재 페이지
    private long totalElementCount; // 게시글 총 개수
    private int currentPageElementCount; // 현재 페이지에 게시글 수

    private List<SimplePostInfo> simpleLectureDtoList = new ArrayList<>();

    public PostPagingDto(Page<Post> searchResults) {
        this.totalPageCount = searchResults.getTotalPages();
        this.currentPageNum = searchResults.getNumber();
        this.totalElementCount = searchResults.getTotalElements();
        this.currentPageElementCount = searchResults.getNumberOfElements();
        this.simpleLectureDtoList = searchResults.getContent().stream().map(SimplePostInfo::new).toList();
    }
}

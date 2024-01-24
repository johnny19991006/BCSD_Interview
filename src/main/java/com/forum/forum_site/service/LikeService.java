package com.forum.forum_site.service;

import com.forum.forum_site.dto.RequestLikeDto;

public interface LikeService {
    void insert(Integer postId, RequestLikeDto requestLikeDto);

    void delete(Integer postId, RequestLikeDto requestLikeDto);

}

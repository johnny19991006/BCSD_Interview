package com.forum.forum_site.service;

import com.forum.forum_site.domain.Comment;
import com.forum.forum_site.dto.SaveCommentDto;
import com.forum.forum_site.dto.UpdateCommentDto;

import java.util.List;

public interface CommentService {
    void saveComment(Integer postId, SaveCommentDto saveCommentDto);

    void saveReComment(Integer postId, Integer parentId, SaveCommentDto saveCommentDto);

    void updateComment(Integer id, UpdateCommentDto updateCommentDto);

    void removeComment(Integer id);
}

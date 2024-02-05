package com.forum.forum_site.service.comment;

import com.forum.forum_site.dto.comment.SaveCommentDto;
import com.forum.forum_site.dto.comment.UpdateCommentDto;

public interface CommentService {
    void saveComment(Integer postId, SaveCommentDto saveCommentDto);

    void saveReComment(Integer postId, Integer parentId, SaveCommentDto saveCommentDto);

    void updateComment(Integer id, UpdateCommentDto updateCommentDto);

    void removeComment(Integer id);
}

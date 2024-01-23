package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Comment;

public record SaveCommentDto (String content) {

    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }
}

package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Post;
import lombok.Getter;

@Getter
public class SimplePostInfo {
    private Integer postId;
    private String title;
    private String content;
    private String writerName;
    private String createdDate;

    public SimplePostInfo(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writerName = post.getAuthor().getUsername();
        this.createdDate = post.getCreated_at().toString();
    }

}

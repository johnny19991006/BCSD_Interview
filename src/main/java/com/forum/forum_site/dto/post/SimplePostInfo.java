package com.forum.forum_site.dto.post;

import com.forum.forum_site.domain.Post;
import lombok.Getter;

@Getter
public class SimplePostInfo {
    private Integer postId;
    private String title;
    private String content;
    private String writerName;
    private String createdDate;
    private Integer likes_count;

    public SimplePostInfo(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writerName = post.getAuthor().getUsername();
        this.createdDate = post.getCreated_at().toString();
        this.likes_count = post.getLikes_count();
    }

}

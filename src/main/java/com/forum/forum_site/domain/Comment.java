package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "Comment")
@Data
@NoArgsConstructor
@Entity
public class Comment {

    @Id @GeneratedValue
    private Integer comment_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private Integer likes_count;

    private boolean is_Removed= false;

    @Builder
    public Comment( User author, Post post, Comment parent, String content) {
        this.author = author;
        this.post = post;
        this.parent = parent;
        this.content = content;
        this.is_Removed = false;
    }
}

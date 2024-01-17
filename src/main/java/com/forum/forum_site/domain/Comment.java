package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Comment")
@Data
@NoArgsConstructor
@Entity
public class Comment {
    @Id @GeneratedValue
    private Integer comment_id;

    @Column(nullable = false)
    private Integer post_id;

    @Column(nullable = false)
    private Integer author_id;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column
    private Integer likes_count;
}

package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Table(name = "Post")
@Data
@NoArgsConstructor
@Entity
public class Post{
    @Id @GeneratedValue
    private Integer post_id;

    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String filePath;

    @ManyToOne(fetch = LAZY) // 지연 로딩 관련 엔티티의 데이터를 다 로드 안해서 성능 최적화 및 트래픽 감소
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;

    @Column(nullable = false)
    private Integer likes_count;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime updated_at;
}

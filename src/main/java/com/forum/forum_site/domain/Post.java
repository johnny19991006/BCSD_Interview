package com.forum.forum_site.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Table(name = "Post")
@Data
@NoArgsConstructor
@Entity
public class Post{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임
    private Integer post_id;

    @Column(length = 40, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String filepath;

    // 익명 게시를 위해 optional = true 추가
    // 지연 로딩 // 관련 entity의 데이터를 다 로드 안해서 성능 최적화 및 트래픽 감소
    @ManyToOne(fetch = LAZY, optional = true)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;

    @Column(nullable = false)
    private Integer likes_count = 0;

    @Column(nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    public void confirmAuthor(User author) {
        this.author = author;
        author.addPost(this);
    }

    public void updateTitle(String title) { this.title = title; }

    public void updateContent(String content) { this.content = content; }
    public void updateFilePath(String filePath) {
        this.filepath = filePath;
    }
}

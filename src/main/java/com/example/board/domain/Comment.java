package com.example.board.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments")
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonBackReference
    private Board board;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "comment_content")
    private String commentContent;

    @PrePersist
    protected void onCreate() {
        updatedAt = LocalDateTime.now();
    }
    public Comment updateCommentContent(String newContent) {
        return Comment.builder()
                .commentId(this.commentId)
                .user(this.user)
                .board(this.board)
                .updatedAt(LocalDateTime.now())
                .commentContent(newContent)
                .build();
    }
}

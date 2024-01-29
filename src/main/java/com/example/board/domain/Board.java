package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private int boardId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "created_at")
    private LocalDateTime createdAt; // 디폴트 현재시간

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 디폴트 현재시간 후 수정할 때마다 갱신

    @Column(name = "board_title", length = 100)
    private String boardTitle;

    @Column(name = "board_price", length = 20)
    private String boardPrice; // 디폴트 consult

    @Column(name = "board_content", length = 255)
    private String boardContent;

    @Column(name = "board_views")
    private int boardViews; // 디폴트 0, 조회 할 때마다 1씩 카운트

    @Enumerated(EnumType.STRING)
    @Column(name = "board_status")
    private BoardStatus boardstatus;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

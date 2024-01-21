package com.example.board.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "board_title", length = 100)
    private String boardTitle;

    @Column(name = "board_price", length = 20)
    private String boardPrice;

    @Column(name = "board_content")
    private String boardContent;

    @Column(name = "board_views")
    private int boardViews;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_status")
    private BoardStatus boardstatus;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<BoardHasHashtag> boardHashtags;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments;
}
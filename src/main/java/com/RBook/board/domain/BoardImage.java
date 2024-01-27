package com.RBook.board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "BoardImage")
public class BoardImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(name = "url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    @Builder
    public BoardImage(Long imageId, String url, Board board) {
        this.imageId = imageId;
        this.url = url;
        this.board = board;
    }
}

package com.RBook.board.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
/*
CREATE TABLE Board (
	board_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    id BIGINT,
    writer VARCHAR(10) NOT NULL,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(15) NOT NULL,
    content VARCHAR(1000) NOT NULL,
    genre VARCHAR(20) NOT NULL,
    create_date DATETIME NOT NUll DEFAULT current_timestamp(),
    modify_date DATETIME DEFAULT NULL,
    image_path VARCHAR(255),
    FOREIGN KEY (id) REFERENCES User(id)
);
 */

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Board")
public class Board extends Time{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column(name = "writer", length = 10, nullable = false)
    private String writer;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "author", length = 15, nullable = false)
    private String author;

    @Column(name = "content", length = 1000, nullable = false)
    private String content;

    @Column(name = "genre", length = 20, nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "id")
    private User userId;

    @OneToMany(mappedBy = "boardId", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("imageId asc")
    private List<BoardImage> boardImage;
    @Builder
    public Board(Long boardId, String writer, String title, String author, String content, Genre genre, User userId, List<BoardImage> boardImage) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.author = author;
        this.content = content;
        this.genre = genre;
        this.userId = userId;
        this.boardImage = boardImage;
    }

}

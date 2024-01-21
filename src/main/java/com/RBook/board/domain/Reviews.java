package com.RBook.board.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
review_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    id INTEGER,
    board_id INTEGER,
    writer VARCHAR(10) NOT NULL,
    comment_text VARCHAR(255) NOT NULL,
    create_date DATETIME NOT NUll,
    FOREIGN KEY (id) REFERENCES User(id),
    FOREIGN KEY (board_id) REFERENCES RBoard(board_id)

 */
@Entity
@Getter
@NoArgsConstructor
@Table(name = "Reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "writer", length = 10, nullable = false)
    private String writer;

    @Column(name = "comment_text", columnDefinition = "TEXT" , nullable = false)
    private String comment;

    @Builder
    public Reviews(Long reviewId, String writer, String comment) {
        this.reviewId = reviewId;
        this.writer = writer;
        this.comment = comment;
    }
}

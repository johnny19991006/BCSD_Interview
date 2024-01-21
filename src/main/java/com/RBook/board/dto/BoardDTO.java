package com.RBook.board.dto;

import com.RBook.board.domain.Board;
import com.RBook.board.domain.Genre;
import com.RBook.board.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@ToString
@NoArgsConstructor
public class BoardDTO {
    private Long boardId;
    private String writer;
    private String title;
    private String author;
    private String content;
    private Genre genre;
    private User userId;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private String image;

    public Board toEntity() {
        Board board = Board.builder()
                .boardId(boardId)
                .writer(writer)
                .title(title)
                .author(author)
                .content(content)
                .genre(genre)
                .userId(userId)
                .image(image)
                .build();
        return board;
    }

    @Builder
    public BoardDTO(Long boardId, String writer, String title, String author, String content, Genre genre, User userId, LocalDateTime createDate, LocalDateTime modifyDate, String image) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.author = author;
        this.content = content;
        this.genre = genre;
        this.userId = userId;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.image = image;
    }

}

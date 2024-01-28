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
    private User user;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;


    public Board toEntity() {
        Board board = Board.builder()
                .boardId(boardId)
                .writer(writer)
                .title(title)
                .author(author)
                .content(content)
                .genre(genre)
                .user(user)
                .build();
        return board;
    }
    /*
    //List<String>타입을 BoardImage타입으로 바꿔서 저장하기 위한 함수
    public List<BoardImage> convertToBoardImageList(List<String> imageUrls) {
        if (imageUrls == null) {
            return Collections.emptyList();
        }

        return imageUrls.stream()
                .map(url -> BoardImage.builder().url(url).build())
                .collect(Collectors.toList());
    }

     */

    @Builder
    public BoardDTO(Long boardId, String writer, String title, String author, String content, Genre genre, User user, LocalDateTime createDate, LocalDateTime modifyDate) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.author = author;
        this.content = content;
        this.genre = genre;
        this.user = user;
        this.createDate = createDate;
        this.modifyDate = modifyDate;

    }

}

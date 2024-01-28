package com.RBook.board.repository;

import com.RBook.board.domain.Board;
import com.RBook.board.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository;

    @Test
    void saveBoard() {
        Board saveParams = Board.builder()
                .writer("당근맛연어")
                .title("원피스")
                .author("오다")
                .content("어쩌고 저쩌고")
                .genre(Genre.판타지)
                .build();

        Board board = boardRepository.save(saveParams);

    }
}
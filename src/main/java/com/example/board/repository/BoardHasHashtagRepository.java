package com.example.board.repository;

import com.example.board.domain.Board;
import com.example.board.domain.BoardHasHashtag;
import com.example.board.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHasHashtagRepository extends JpaRepository<BoardHasHashtag, Integer> {
    void deleteByBoardAndHashtag(Board board, Hashtag hashtag);
}

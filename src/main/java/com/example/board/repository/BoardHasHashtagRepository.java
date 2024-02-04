package com.example.board.repository;

import com.example.board.domain.Board;
import com.example.board.domain.BoardHasHashtag;
import com.example.board.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardHasHashtagRepository extends JpaRepository<BoardHasHashtag, Integer> {
    void deleteByBoardAndHashtag(Board board, Hashtag hashtag);
    @Query("SELECT bh FROM BoardHasHashtag bh WHERE bh.board.boardId = :boardId")
    List<BoardHasHashtag> findByBoardId(@Param("boardId") int boardId);
}

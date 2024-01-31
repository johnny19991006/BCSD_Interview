package com.example.board.repository;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    List<Hashtag> findAllByOrderByHashtagIdAsc();
    @Query("SELECT bh.board FROM BoardHasHashtag bh WHERE bh.hashtag.hashtagId = :hashtagId")
    List<Board> findBoardsByHashtagId(int hashtagId);
}

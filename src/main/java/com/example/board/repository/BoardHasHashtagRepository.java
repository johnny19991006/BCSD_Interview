package com.example.board.repository;

import com.example.board.domain.BoardHasHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface BoardHasHashtagRepository extends JpaRepository<BoardHasHashtag, Long> {
}

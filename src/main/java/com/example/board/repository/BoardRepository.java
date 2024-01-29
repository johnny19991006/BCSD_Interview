package com.example.board.repository;

import com.example.board.domain.Board;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByOrderByBoardIdAsc();
    List<Board> findTop5ByOrderByCreatedAtDesc(Pageable pageable);
    List<Board> findTop5ByOrderByBoardViewsDesc(Pageable pageable);
    List<Board> findByBoardTitleContaining(String title);
}

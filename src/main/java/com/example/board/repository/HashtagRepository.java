package com.example.board.repository;

import com.example.board.domain.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Integer> {
    List<Hashtag> findAllByOrderByHashtagIdAsc();
}

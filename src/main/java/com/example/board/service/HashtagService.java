package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface HashtagService {
    public Hashtag insertHashtag(Hashtag hashtag) throws Exception;
    public List<Hashtag> getAllHashtags();
    public void deleteHashtag(Integer hashtagId) throws EmptyResultDataAccessException;
    public List<Board> getBoardsByHashtagId(int hashtagId);
}

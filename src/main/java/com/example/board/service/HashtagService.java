package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;

import java.sql.SQLException;
import java.util.List;

public interface HashtagService {
    public Hashtag insertHashtag(Hashtag hashtag) throws SQLException;
    public List<Hashtag> getAllHashtags() throws SQLException;
    public void deleteHashtag(Integer hashtagId) throws SQLException;
    public List<Board> getBoardsByHashtagId(int hashtagId) throws SQLException;
}

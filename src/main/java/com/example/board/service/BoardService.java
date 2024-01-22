package com.example.board.service;

import com.example.board.domain.Board;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    public Board insertBoard(Board board) throws SQLException;

    public void deleteBoard(Integer boardId) throws SQLException;

    // 게시글 수정
    // 최신 게시글 조회
    // 인기 게시글 조회
}

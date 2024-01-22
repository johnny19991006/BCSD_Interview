package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository repository) {
        this.boardRepository = repository;
    }
    @Override
    public Board insertBoard(Board board) throws SQLException { // 게시글 추가
        return this.boardRepository.save(board);
    }


    @Override
    public void deleteBoard(Integer boardId) throws SQLException { // 게시글 삭제
        boardRepository.deleteById(boardId);
    }
}

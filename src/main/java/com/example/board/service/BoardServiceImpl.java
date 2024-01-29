package com.example.board.service;

import com.example.board.domain.*;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CategoryRepository categoryRepository) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Board insertBoard(Board board) throws SQLException { // 게시글 추가
        return this.boardRepository.save(board);
    }
    @Override
    public List<Board> getAllBoards() throws SQLException {
        List<Board> boards = boardRepository.findAllByOrderByBoardIdAsc();
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public Board getBoardByBoardId(Integer boardId) throws SQLException {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board != null) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return board;
    }
    @Override
    public List<Board> getLatestBoards() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Board> boards = boardRepository.findTop5ByOrderByCreatedAtDesc(pageable);
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public List<Board> getPopularBoards() throws SQLException {
        Pageable pageable = PageRequest.of(0, 5);
        List<Board> boards = boardRepository.findTop5ByOrderByBoardViewsDesc(pageable);
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public List<Board> getBoardsByTitleContaining(String title) throws SQLException {
        List<Board> boards = boardRepository.findByBoardTitleContaining(title);
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }

    @Override
    public void updateBoardTitle(Integer boardId, String newTitle) throws SQLException{
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardTitle(newTitle);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardPrice(Integer boardId, String newPrice) throws SQLException{
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardPrice(newPrice);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardContent(Integer boardId, String newContent) throws SQLException{
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardContent(newContent);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardStatus(Integer boardId, String newStatus) throws SQLException{
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            BoardStatus status = BoardStatus.valueOf(newStatus);
            boardInf.setBoardstatus(status);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardCategory(Integer boardId, Integer newCgNum) throws SQLException{
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        Category newCategory = categoryRepository.findById(newCgNum).orElse(null);
        if(boardInf != null && newCategory != null) {
            boardInf.setCategory(newCategory);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void deleteBoard(Integer boardId) throws SQLException { // 게시글 삭제
        boardRepository.deleteById(boardId);
    }
}

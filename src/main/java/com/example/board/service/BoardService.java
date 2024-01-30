package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardDTO;

import java.sql.SQLException;
import java.util.List;

public interface BoardService {
    public Board insertBoard(BoardDTO boardDTO) throws SQLException;
    public List<Board> getAllBoards() throws SQLException;
    public Board getBoardByBoardId(Integer boardId) throws SQLException;
    public List<Board> getLatestBoards() throws SQLException;
    public List<Board> getPopularBoards() throws SQLException;
    public List<Board> getBoardsByTitleContaining(String title) throws SQLException;
    public List<Board> getBoardByUserId(int userId) throws SQLException;
    public List<Board> getBoardByCategoryId(int categoryId) throws SQLException;
    public void updateBoardTitle(Integer boardId, String newTitle) throws SQLException;
    public void updateBoardPrice(Integer boardId, String newPrice) throws SQLException;
    public void updateBoardContent(Integer boardId, String newContent) throws SQLException;
    public void updateBoardCategory(Integer boardId, Integer newCgNum) throws SQLException;
    public void updateBoardStatus(Integer boardId, String newStatus) throws SQLException;
    public void deleteBoard(Integer boardId) throws SQLException;
    public void addHashtagToBoard(int boardId, int hashtagId) throws SQLException;
    public void removeHashtagFromBoard(int boardId, int hashtagId) throws SQLException;
}

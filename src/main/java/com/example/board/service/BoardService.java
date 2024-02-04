package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import com.example.board.dto.BoardRequestDTO;

import java.util.List;

public interface BoardService {
    public Board insertBoard(BoardRequestDTO boardRequestDTO) ;
    public List<Board> getAllBoards() ;
    public Board getBoardByBoardId(Integer boardId) ;
    public List<Board> getLatestBoards() ;
    public List<Board> getPopularBoards() ;
    public List<Board> getBoardsByTitleContaining(String title) ;
    public List<Board> getBoardByUserId(int userId) ;
    public List<Board> getBoardByCategoryId(int categoryId) ;
    public void updateBoardTitle(Integer boardId, String newTitle) ;
    public void updateBoardPrice(Integer boardId, String newPrice) ;
    public void updateBoardContent(Integer boardId, String newContent) ;
    public void updateBoardCategory(Integer boardId, Integer newCgNum) ;
    public void updateBoardStatus(Integer boardId, String newStatus) ;
    public void deleteBoard(Integer boardId) ;
    public void addHashtagToBoard(int boardId, int hashtagId) ;
    public void removeHashtagFromBoard(int boardId, int hashtagId) ;
    public List<Hashtag> getHashtagsForBoard(int boardId) ;
    public Board getBoardById(Integer boardId);
}

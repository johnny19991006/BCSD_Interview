package com.example.board.service;

import com.example.board.domain.*;
import com.example.board.dto.BoardDTO;
import com.example.board.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final HashtagRepository hashtagRepository;
    private final UserRepository userRepository;
    private final BoardHasHashtagRepository boardHasHashtagRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, CategoryRepository categoryRepository, HashtagRepository hashtagRepository, UserRepository userRepository, BoardHasHashtagRepository boardHasHashtagRepository) {
        this.boardRepository = boardRepository;
        this.categoryRepository = categoryRepository;
        this.hashtagRepository = hashtagRepository;
        this.userRepository = userRepository;
        this.boardHasHashtagRepository = boardHasHashtagRepository;
    }
    @Override
    @Transactional
    public Board insertBoard(BoardDTO boardDTO) throws SQLException {
        User user = userRepository.findById(boardDTO.getUserId()).orElse(null);
        Category category = categoryRepository.findById(boardDTO.getCategoryId()).orElse(null);

        Board board = new Board();
        board.setUser(user);
        board.setCategory(category);
        board.setBoardTitle(boardDTO.getBoardTitle());
        board.setBoardPrice(boardDTO.getBoardPrice());
        board.setBoardContent(boardDTO.getBoardContent());
        board.setBoardstatus(boardDTO.getBoardStatus());

        List<Integer> hashtagIds = boardDTO.getHashtagIds();

        List<BoardHasHashtag> boardHasHashtags = new ArrayList<>();

        for (Integer hashtagId : hashtagIds) {
            Hashtag hashtag = hashtagRepository.findById(hashtagId).orElse(null);
            BoardHasHashtag boardHasHashtag = new BoardHasHashtag();
            boardHasHashtag.setBoard(board);
            boardHasHashtag.setHashtag(hashtag);
            boardHasHashtags.add(boardHasHashtag);
        }
        board.setBoardHashtags(boardHasHashtags);

        boardRepository.save(board);  // Board 저장
        boardHasHashtagRepository.saveAll(boardHasHashtags);

        return board;
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
    public List<Board> getBoardByUserId(int userId) throws SQLException {
        return boardRepository.findByUserUserId(userId);
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
    @Override
    public List<Board> getBoardByCategoryId(int categoryId) throws SQLException {
        return boardRepository.findByCategoryCategoryId(categoryId);
    }
    @Override
    public void addHashtagToBoard(int boardId, int hashtagId) throws SQLException {
        Board board = boardRepository.findById(boardId).orElse(null);
        Hashtag hashtag = hashtagRepository.findById(hashtagId).orElse(null);

        if(board != null && hashtag != null) {
            BoardHasHashtag boardHasHashtag = new BoardHasHashtag();
            boardHasHashtag.setBoard(board);
            boardHasHashtag.setHashtag(hashtag);

            boardHasHashtagRepository.save(boardHasHashtag);
        }
    }
    @Override
    @Transactional
    public void removeHashtagFromBoard(int boardId, int hashtagId) throws SQLException {
        Board board = boardRepository.findById(boardId).orElse(null);
        Hashtag hashtag = hashtagRepository.findById(hashtagId).orElse(null);

        if (board != null && hashtag != null) {
            boardHasHashtagRepository.deleteByBoardAndHashtag(board, hashtag);
        }
    }
}

package com.example.board.service;

import com.example.board.domain.*;
import com.example.board.dto.BoardRequestDTO;
import com.example.board.exception.UnauthorizedException;
import com.example.board.repository.*;
import com.example.board.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Board insertBoard(BoardRequestDTO boardRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if(userDetails.getUser().getUserId() == boardRequestDTO.getUserId()) {
            User user = userRepository.findById(boardRequestDTO.getUserId()).orElse(null);
            Category category = categoryRepository.findById(boardRequestDTO.getCategoryId()).orElse(null);

            Board board = new Board();
            board.setUser(user);
            board.setCategory(category);
            board.setBoardTitle(boardRequestDTO.getBoardTitle());
            board.setBoardPrice(boardRequestDTO.getBoardPrice());
            board.setBoardContent(boardRequestDTO.getBoardContent());
            board.setBoardstatus(boardRequestDTO.getBoardStatus());

            List<Integer> hashtagIds = boardRequestDTO.getHashtagIds();

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
        else {
            throw new UnauthorizedException("Unauthorized access");
        }
    }
    @Override
    public List<Board> getAllBoards() {
        List<Board> boards = boardRepository.findAllByOrderByBoardIdAsc();
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public Board getBoardByBoardId(Integer boardId) {
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
    public List<Board> getPopularBoards() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Board> boards = boardRepository.findTop5ByOrderByBoardViewsDesc(pageable);
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public List<Board> getBoardsByTitleContaining(String title)  {
        List<Board> boards = boardRepository.findByBoardTitleContaining(title);
        for (Board board : boards) {
            board.setBoardViews(board.getBoardViews() + 1);
            boardRepository.save(board);
        }
        return boards;
    }
    @Override
    public List<Board> getBoardByUserId(int userId) {
        return boardRepository.findByUserUserId(userId);
    }

    @Override
    public void updateBoardTitle(Integer boardId, String newTitle) {
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardTitle(newTitle);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardPrice(Integer boardId, String newPrice) {
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardPrice(newPrice);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardContent(Integer boardId, String newContent) {
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            boardInf.setBoardContent(newContent);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardStatus(Integer boardId, String newStatus) {
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        if(boardInf != null) {
            BoardStatus status = BoardStatus.valueOf(newStatus);
            boardInf.setBoardstatus(status);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void updateBoardCategory(Integer boardId, Integer newCgNum) {
        Board boardInf = boardRepository.findById(boardId).orElse(null);
        Category newCategory = categoryRepository.findById(newCgNum).orElse(null);
        if(boardInf != null && newCategory != null) {
            boardInf.setCategory(newCategory);
            boardRepository.save(boardInf);
        }
    }
    @Override
    public void deleteBoard(Integer boardId)  { // 게시글 삭제
        boardRepository.deleteById(boardId);
    }
    @Override
    public List<Board> getBoardByCategoryId(int categoryId) {
        return boardRepository.findByCategoryCategoryId(categoryId);
    }
    @Override
    public void addHashtagToBoard(int boardId, int hashtagId) {
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
    public void removeHashtagFromBoard(int boardId, int hashtagId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        Hashtag hashtag = hashtagRepository.findById(hashtagId).orElse(null);

        if (board != null && hashtag != null) {
            boardHasHashtagRepository.deleteByBoardAndHashtag(board, hashtag);
        }
    }
    @Override
    public List<Hashtag> getHashtagsForBoard(int boardId) {
        return boardRepository.findHashtagsByBoardId(boardId);
    }
    @Override
    public Board getBoardById(Integer boardId) {
        return boardRepository.findById(boardId).orElse(null);
    }
}

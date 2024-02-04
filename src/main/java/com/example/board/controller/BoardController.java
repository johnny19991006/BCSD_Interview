package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import com.example.board.dto.BoardRequestDTO;
import com.example.board.security.AuthorizeBoard;
import com.example.board.security.AuthorizeUser;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;
    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    @PostMapping
    public Board insertBoard(@RequestBody BoardRequestDTO boardRequestDTO){
        return boardService.insertBoard(boardRequestDTO);
    }
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }
    @GetMapping("/{boardId}")
    public Board getBoardByBoardId(@PathVariable Integer boardId) {
        return boardService.getBoardByBoardId(boardId);
    }
    @GetMapping("/latest")
    public List<Board> getLatestBoards() {
        return boardService.getLatestBoards();
    }
    @GetMapping("/popular")
    public List<Board> getPopularBoards() {
        return boardService.getPopularBoards();
    }
    @GetMapping(params = "title")
    public List<Board> getBoardsByTitleContaining(@RequestParam String title) {
        return boardService.getBoardsByTitleContaining(title);
    }
    @AuthorizeUser
    @GetMapping("/user/{userId}")
    public List<Board> getBoardByUserId(@PathVariable int userId) {
        return boardService.getBoardByUserId(userId);
    }
    @GetMapping("/category/{categoryId}")
    public List<Board> getBoardByCategoryId(@PathVariable int categoryId)  {
        return boardService.getBoardByCategoryId(categoryId);
    }
    @PutMapping("/{boardId}/title")
    @AuthorizeBoard
    public void updateBoardTitle(@PathVariable Integer boardId, @RequestBody String newTitle) {
        boardService.updateBoardTitle(boardId, newTitle);
    }

    @PutMapping("/{boardId}/price")
    @AuthorizeBoard
    public void updateBoardPrice(@PathVariable Integer boardId, @RequestBody String newPrice) {
        boardService.updateBoardPrice(boardId, newPrice);
    }

    @PutMapping("/{boardId}/content")
    @AuthorizeBoard
    public void updateBoardContent(@PathVariable Integer boardId, @RequestBody String newContent) {
        boardService.updateBoardContent(boardId, newContent);
    }

    @PutMapping("/{boardId}/status")
    @AuthorizeBoard
    public void updateBoardStatus(@PathVariable Integer boardId, @RequestBody String newStatus) {
        boardService.updateBoardStatus(boardId, newStatus);
    }

    @PutMapping("/{boardId}/category")
    @AuthorizeBoard
    public void updateBoardCategory(@PathVariable Integer boardId, @RequestBody Integer newCgNum) {
        boardService.updateBoardCategory(boardId, newCgNum);
    }

    @DeleteMapping("/{boardId}")
    @AuthorizeBoard
    public void deleteBoard(@PathVariable Integer boardId)  {
        boardService.deleteBoard(boardId);
    }
    @PostMapping("/{boardId}/hashtag/{hashtagId}")
    @AuthorizeBoard
    public void addHashtagToBoard(@PathVariable int boardId, @PathVariable int hashtagId) {
        boardService.addHashtagToBoard(boardId, hashtagId);
    }

    @DeleteMapping("/{boardId}/hashtag/{hashtagId}")
    @AuthorizeBoard
    public void removeHashtagFromBoard(@PathVariable int boardId, @PathVariable int hashtagId) {
        boardService.removeHashtagFromBoard(boardId, hashtagId);
    }

    @GetMapping("/{boardId}/hashtags")
    public List<Hashtag> getHashtagsForBoard(@PathVariable int boardId) {
        return boardService.getHashtagsForBoard(boardId);
    }
}
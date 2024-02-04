package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.domain.Hashtag;
import com.example.board.dto.BoardRequestDTO;
import com.example.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Board> insertBoard(@RequestBody BoardRequestDTO boardRequestDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(boardService.insertBoard(boardRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        return ResponseEntity.ok().body(boardService.getAllBoards());
    }
    @GetMapping("/{boardId}")
    public ResponseEntity<Board> getBoardByBoardId(@PathVariable Integer boardId) {
        return ResponseEntity.ok().body(boardService.getBoardByBoardId(boardId));
    }
    @GetMapping("/latest")
    public ResponseEntity<List<Board>> getLatestBoards() {
        return ResponseEntity.ok().body(boardService.getLatestBoards());
    }
    @GetMapping("/popular")
    public ResponseEntity<List<Board>> getPopularBoards() {
        return ResponseEntity.ok().body(boardService.getPopularBoards());
    }
    @GetMapping(params = "title")
    public ResponseEntity<List<Board>> getBoardsByTitleContaining(@RequestParam String title) {
        return ResponseEntity.ok().body(boardService.getBoardsByTitleContaining(title));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Board>> getBoardByUserId(@PathVariable int userId) {
        return ResponseEntity.ok().body(boardService.getBoardByUserId(userId));
    }
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Board>> getBoardByCategoryId(@PathVariable int categoryId)  {
        return ResponseEntity.ok().body(boardService.getBoardByCategoryId(categoryId));
    }
    @PutMapping("/{boardId}/title")
    public ResponseEntity<Void> updateBoardTitle(@PathVariable Integer boardId, @RequestBody String newTitle) {
        boardService.updateBoardTitle(boardId, newTitle);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}/price")
    public ResponseEntity<Void> updateBoardPrice(@PathVariable Integer boardId, @RequestBody String newPrice) {
        boardService.updateBoardPrice(boardId, newPrice);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}/content")
    public ResponseEntity<Void> updateBoardContent(@PathVariable Integer boardId, @RequestBody String newContent) {
        boardService.updateBoardContent(boardId, newContent);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}/status")
    public ResponseEntity<Void> updateBoardStatus(@PathVariable Integer boardId, @RequestBody String newStatus) {
        boardService.updateBoardStatus(boardId, newStatus);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{boardId}/category")
    public ResponseEntity<Void> updateBoardCategory(@PathVariable Integer boardId, @RequestBody Integer newCgNum) {
        boardService.updateBoardCategory(boardId, newCgNum);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Integer boardId)  {
        boardService.deleteBoard(boardId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{boardId}/hashtag/{hashtagId}")
    public ResponseEntity<Void> addHashtagToBoard(@PathVariable int boardId, @PathVariable int hashtagId) {
        boardService.addHashtagToBoard(boardId, hashtagId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{boardId}/hashtag/{hashtagId}")
    public ResponseEntity<Void> removeHashtagFromBoard(@PathVariable int boardId, @PathVariable int hashtagId) {
        boardService.removeHashtagFromBoard(boardId, hashtagId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{boardId}/hashtags")
    public ResponseEntity<List<Hashtag>> getHashtagsForBoard(@PathVariable int boardId) {
        return ResponseEntity.ok().body(boardService.getHashtagsForBoard(boardId));
    }
}
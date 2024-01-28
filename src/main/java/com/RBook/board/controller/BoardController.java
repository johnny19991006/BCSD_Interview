package com.RBook.board.controller;


import com.RBook.board.dto.BoardDTO;
import com.RBook.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("board")
public class BoardController {
    private BoardService boardService;


    @GetMapping({"", "/list"})
    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer pageNum) {
        List<BoardDTO> boardList = boardService.getBoardList(pageNum);
        Integer[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageList", pageList);

        return "board/list";
    }

    @GetMapping("/post")
    public String write() {return "board/write";}

    @PostMapping("/post")
    public String write(BoardDTO boardDTO) {

        boardService.saveBoard(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model) {
        BoardDTO boardDTO = boardService.getBoard(no);

        model.addAttribute("boardDTO", boardDTO);
        return "board/detail";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model) {
        BoardDTO boardDTO = boardService.getBoard(no);

        model.addAttribute("boardDTO", boardDTO);
        return "board/update";
    }
    @PostMapping ("/post/edit/{no}")
    public String update(BoardDTO boardDTO) {
        Long userId = boardDTO.getUser().getId();
        boardService.modifyBoard(boardDTO, userId);

        return "redirect:/board/list";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deleteBoard(no);

        return "redirect:/board/list";
    }

    @GetMapping("/board/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model) {
        List<BoardDTO> boardDTOList = boardService.searchBoards(keyword);

        model.addAttribute("boardList", boardDTOList);

        return "board/list";
    }




}

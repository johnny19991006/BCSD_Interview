package com.example.board.dto;

import com.example.board.domain.BoardStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardRequestDTO {
    private int userId;
    private int categoryId;
    private String boardTitle;
    private String boardPrice;
    private String boardContent;
    private BoardStatus boardStatus;
    private List<Integer> hashtagIds;
}

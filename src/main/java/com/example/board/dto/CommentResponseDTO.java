package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentResponseDTO {
    private int commentId;
    private int userId;
    private int boardId;
    private LocalDateTime updatedAt;
    private String commentContent;
}

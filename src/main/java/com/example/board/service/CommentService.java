package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.dto.CommentRequestDTO;
import com.example.board.dto.CommentResponseDTO;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    public CommentResponseDTO insertComment(CommentRequestDTO commentRequestDTO); // 댓글 작성
    public void deleteComment(Integer commentId); // 댓글 삭제
    public CommentResponseDTO updateCommentContent(Integer commentId, String newContent); // 댓글
    public List<CommentResponseDTO> getCommentByUserId(int userId);
    public Comment getCommentById(Integer commentId);
}

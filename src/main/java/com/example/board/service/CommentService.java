package com.example.board.service;

import com.example.board.domain.Comment;

import java.sql.SQLException;
import java.util.List;

public interface CommentService {
    public Comment insertComment(Comment comment) throws SQLException; // 댓글 작성
    public void deleteComment(Integer commentId) throws SQLException; // 댓글 삭제
    public void updateCommentContent(Integer commentId, String newContent) throws SQLException; // 댓글
    public List<Comment> getCommentByUserId(int userId) throws SQLException;
    public Comment getCommentById(Integer commentId);
}

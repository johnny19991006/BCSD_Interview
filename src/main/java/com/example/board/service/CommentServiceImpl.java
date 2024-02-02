package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository repository) {
        this.commentRepository = repository;
    }
    @Override
    public Comment insertComment(Comment comment) throws SQLException {
        return commentRepository.save(comment);
    }
    @Override
    public void deleteComment(Integer commentId) throws SQLException {
        commentRepository.deleteById(commentId);
    }
    @Override
    public void updateCommentContent(Integer commentId, String newContent) throws SQLException {
        Comment commentInf = commentRepository.findById(commentId).orElse(null);
        if(commentInf != null) {
            commentInf.setCommentContent(newContent);
            commentRepository.save(commentInf);
        }
    }
    @Override
    public List<Comment> getCommentByUserId(int userId) throws SQLException {
        return commentRepository.findByUserUserId(userId);
    }
    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
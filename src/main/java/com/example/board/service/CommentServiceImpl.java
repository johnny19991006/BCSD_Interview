package com.example.board.service;

import com.example.board.domain.Comment;
import com.example.board.exception.UnauthorizedException;
import com.example.board.repository.CommentRepository;
import com.example.board.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (userDetails.getUser().getUserId() == comment.getUser().getUserId()) {
            return commentRepository.save(comment);
        }
        else {
            throw new UnauthorizedException("Unauthorized access");
        }
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
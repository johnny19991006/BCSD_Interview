package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.domain.Comment;
import com.example.board.domain.User;
import com.example.board.dto.CommentRequestDTO;
import com.example.board.dto.CommentResponseDTO;
import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import com.example.board.security.AuthorizeComment;
import com.example.board.security.AuthorizeUser;
import com.example.board.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }
    @Override
    public CommentResponseDTO insertComment(CommentRequestDTO commentRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(commentRequestDTO.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + commentRequestDTO.getUserId()));
        Board board = boardRepository.findById(commentRequestDTO.getBoardId())
                .orElseThrow(() -> new NotFoundException("Board not found with ID: " + commentRequestDTO.getBoardId()));

        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .commentContent(commentRequestDTO.getCommentContent())
                .build();

        if (userDetails.getUser().getUserId() == comment.getUser().getUserId()) {
            Comment savedComment = commentRepository.save(comment);
            return CommentResponseDTO.builder()
                    .commentId(savedComment.getCommentId())
                    .userId(savedComment.getUser().getUserId())
                    .boardId(savedComment.getBoard().getBoardId())
                    .commentContent(savedComment.getCommentContent())
                    .updatedAt(savedComment.getUpdatedAt())
                    .build();
        }
        else {
            throw new UnauthorizedException("Unauthorized access");
        }
    }
    @AuthorizeComment
    @Override
    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
    @AuthorizeComment
    @Override
    public CommentResponseDTO updateCommentContent(Integer commentId, String newContent) {
        Comment commentInf = getCommentById(commentId);
        Comment savedComment = commentRepository.save(commentInf.updateCommentContent(newContent));
        return CommentResponseDTO.builder()
                .commentId(savedComment.getCommentId())
                .userId(savedComment.getUser().getUserId())
                .boardId((savedComment.getBoard().getBoardId()))
                .commentContent(savedComment.getCommentContent())
                .updatedAt(savedComment.getUpdatedAt())
                .build();
    }
    @AuthorizeUser
    @Override
    public List<CommentResponseDTO> getCommentByUserId(int userId) {
        List<Comment> comments = commentRepository.findByUserUserId(userId);
        List<CommentResponseDTO> commentResponseDTOs = new ArrayList<>();

        for (Comment comment : comments) {
            CommentResponseDTO commentResponseDTO = CommentResponseDTO.builder()
                    .commentId(comment.getCommentId())
                    .userId(comment.getUser().getUserId())
                    .boardId(comment.getBoard().getBoardId())
                    .updatedAt(comment.getUpdatedAt())
                    .commentContent(comment.getCommentContent())
                    .build();

            commentResponseDTOs.add(commentResponseDTO);
        }
        return commentResponseDTOs;
    }
    @Override
    public Comment getCommentById(Integer commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }
}
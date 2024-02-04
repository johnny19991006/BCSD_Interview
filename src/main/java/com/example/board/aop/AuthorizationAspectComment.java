package com.example.board.aop;

import com.example.board.domain.Comment;
import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.security.CustomUserDetails;
import com.example.board.service.CommentService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspectComment {
    @Pointcut("@annotation(com.example.board.security.AuthorizeComment) && args(commentId, ..)")
    public void authorizeCommentIdPointcut(Integer commentId) {
    }

    @Before("authorizeCommentIdPointcut(commentId)")
    public void beforeAuthorizeCommentId(Integer commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Comment comment = commentService.getCommentById(commentId);

        if (comment != null) {
            Integer loggedInUserId = userDetails.getUser().getUserId();
            Integer commentUserId = comment.getUser().getUserId();

            if (!loggedInUserId.equals(commentUserId)) {
                throw new UnauthorizedException("댓글에 접근 권한이 없습니다");
            }
        }
        else {
            throw new NotFoundException("댓글을 찾을 수 없습니다");
        }
    }

    @Autowired
    private CommentService commentService;
}

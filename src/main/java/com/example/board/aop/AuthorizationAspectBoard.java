package com.example.board.aop;

import com.example.board.domain.Board;
import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.security.CustomUserDetails;
import com.example.board.service.BoardService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspectBoard {
    @Pointcut("@annotation(com.example.board.security.AuthorizeBoard) && args(boardId, ..)")
    public void authorizeBoardIdPointcut(Integer boardId) {
    }

    @Before("authorizeBoardIdPointcut(boardId)")
    public void beforeAuthorizeBoardId(Integer boardId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Board board = boardService.getBoardById(boardId);

        if (board != null) {
            Integer loggedInUserId = userDetails.getUser().getUserId();
            Integer boardUserId = board.getUser().getUserId();

            if (!loggedInUserId.equals(boardUserId)) {
                throw new UnauthorizedException("Unauthorized access");
            }
        }
        else {
            throw new NotFoundException("Board not found");
        }
    }

    @Autowired
    private BoardService boardService;
}

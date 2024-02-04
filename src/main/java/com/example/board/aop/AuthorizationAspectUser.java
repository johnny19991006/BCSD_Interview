package com.example.board.aop;

import com.example.board.domain.User;
import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.security.CustomUserDetails;
import com.example.board.service.UserService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorizationAspectUser {
    @Pointcut("@annotation(com.example.board.security.AuthorizeUser) && args(userId, ..)")
    public void authorizeUserIdPointcut(Integer userId) {
    }
    @Before("authorizeUserIdPointcut(userId)")
    public void beforeAuthorizeUserId(Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userService.getUserById(userId);

        if(user != null) {
            Integer loggedInUserId = userDetails.getUser().getUserId();
            if (!loggedInUserId.equals(userId)) {
                throw new UnauthorizedException("사용자 접근 권한이 없습니다");
            }
        }
        else {
            throw new NotFoundException("유저를 찾을 수 없습니다");
        }
    }

    @Autowired
    private UserService userService;
}

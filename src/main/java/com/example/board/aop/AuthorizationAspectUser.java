package com.example.board.aop;

import com.example.board.exception.UnauthorizedException;
import com.example.board.security.CustomUserDetails;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
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

        Integer loggedInUserId = userDetails.getUser().getUserId();

        if (!userId.equals(loggedInUserId)) {
            throw new UnauthorizedException("Unauthorized access");
        }
    }
}

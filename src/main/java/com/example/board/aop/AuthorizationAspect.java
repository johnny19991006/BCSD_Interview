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
public class AuthorizationAspect {
    @Pointcut("@annotation(com.example.board.security.Authorize) && args(userId,..)")
    public void authorizePointcut(Integer userId) {
    }

    @Before("authorizePointcut(userId)")
    public void beforeAuthorize(Integer userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Integer loggedInUserId = userDetails.getUser().getUserId();

        if (!userId.equals(loggedInUserId)) {
            throw new UnauthorizedException("Unauthorized access");
        }
    }
}

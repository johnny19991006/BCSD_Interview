package com.forum.forum_site.service;

import com.forum.forum_site.domain.User;
import com.forum.forum_site.exception.UserException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseService {
    // 현재 로그인한 유저의 정보를 가져오는 메소드
    protected User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new UserException(UserException.Type.NOT_FOUND_MEMBER);
    }
}

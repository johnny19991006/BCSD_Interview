package io.github.imtotem.shortly.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Checker {

    public boolean isSelf(String email) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.isAuthenticated() && email.equalsIgnoreCase(auth.getName());
    }

}

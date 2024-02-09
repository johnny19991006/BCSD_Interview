package io.github.imtotem.shortly.security;

import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException(ErrorCode.USER_NOT_FOUND.getMessage())
            );
    }
}

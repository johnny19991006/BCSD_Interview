package io.github.imtotem.shortly.service.user.login;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.JwtToken;
import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.exception.UserException;
import io.github.imtotem.shortly.repository.UserRepository;
import io.github.imtotem.shortly.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final JwtUtil util;

    @Override
    public JwtToken login(User request) throws RuntimeException {

        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.EMAIL_NOT_FOUND.getMessage()));

        if(!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserException(ErrorCode.WRONG_PASSWORD);
        }

        return JwtToken.builder()
                .token(util.generateToken(user.getUsername()))
                .build();
    }
}

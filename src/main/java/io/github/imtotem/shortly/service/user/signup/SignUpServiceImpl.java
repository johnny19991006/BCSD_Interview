package io.github.imtotem.shortly.service.user.signup;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.exception.UserException;
import io.github.imtotem.shortly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public User createUser(User request) throws RuntimeException {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserException(ErrorCode.ALREADY_SAVED_EMAIL);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .build();

        return repository.save(user);
    }
}

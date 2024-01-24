package io.github.imtotem.shortly.service.user;

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
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Override
    public User findUser(long id) throws RuntimeException {
        // TODO 권한 체크
        return repository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User updateUser(User request) throws RuntimeException {
        User user = repository.findById(request.getId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        User newUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(encoder.encode(request.getPassword()))
                .build();

        return repository.save(newUser);
    }

    @Override
    public boolean deleteUser(User request) throws RuntimeException {
        if (!repository.existsById(request.getId())) {
            throw new UserException(ErrorCode.EMAIL_NOT_FOUND);
        }

        repository.deleteByEmail(request.getEmail());
        return true;
    }
}

package io.github.imtotem.shortly.service.user;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.exception.ErrorCode;
import io.github.imtotem.shortly.exception.UserException;
import io.github.imtotem.shortly.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

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
        // TODO 권한 체크
        User user = repository.findById(request.getId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        User newUser = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(encoder.encode(request.getPassword()))
                .createdAt(user.getCreatedAt())
                .build();

        return repository.save(newUser);
    }

    @Override
    public boolean deleteUser(User request) throws RuntimeException {
        // TODO 권한 체크
        if (!repository.existsById(request.getId())) {
            throw new UserException(ErrorCode.EMAIL_NOT_FOUND);
        }

        repository.deleteByEmail(request.getEmail());
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException(ErrorCode.USER_NOT_FOUND.getMessage())
            );
    }
}

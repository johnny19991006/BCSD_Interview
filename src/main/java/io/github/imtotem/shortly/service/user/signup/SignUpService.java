package io.github.imtotem.shortly.service.user.signup;

import io.github.imtotem.shortly.domain.User;

public interface SignUpService {
    User createUser(User request) throws RuntimeException;
}

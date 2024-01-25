package io.github.imtotem.shortly.service.user.login;

import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.dto.JwtToken;

public interface LoginService {
    JwtToken login(User request) throws RuntimeException;
}

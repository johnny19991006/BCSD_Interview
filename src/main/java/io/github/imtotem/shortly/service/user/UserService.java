package io.github.imtotem.shortly.service.user;

import io.github.imtotem.shortly.domain.User;

public interface UserService {
    User findUser(String email) throws RuntimeException;
    User updateUser(User request) throws RuntimeException;
    boolean deleteUser(User request) throws RuntimeException;
}

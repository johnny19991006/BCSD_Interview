package io.github.imtotem.shortly.service.url;

import io.github.imtotem.shortly.domain.UserUrl;

import java.util.List;

public interface UrlService {
    List<UserUrl> findAll(String email) throws RuntimeException;

    UserUrl createUrl(UserUrl request) throws RuntimeException;

    UserUrl updateUrl(UserUrl request) throws RuntimeException;

    boolean deleteUrl(UserUrl request) throws RuntimeException;
}

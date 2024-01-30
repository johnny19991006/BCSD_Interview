package io.github.imtotem.shortly.service.admin;

import io.github.imtotem.shortly.domain.Url;

import java.util.List;

public interface AdminService {
    List<Url> findAll() throws RuntimeException;

    boolean deleteUrl(Url url) throws RuntimeException;
}

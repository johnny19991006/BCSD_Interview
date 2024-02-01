package io.github.imtotem.shortly.service.notice;

import io.github.imtotem.shortly.domain.Notice;
import io.github.imtotem.shortly.domain.User;

import java.util.List;

public interface NoticeService {
    Notice createNotice(Notice request) throws RuntimeException;

    List<Notice> findAll() throws RuntimeException;

    List<Notice> findAllByUser(User user) throws RuntimeException;

    boolean deleteNotice(long id) throws RuntimeException;
}

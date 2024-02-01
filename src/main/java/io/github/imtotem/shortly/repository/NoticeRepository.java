package io.github.imtotem.shortly.repository;

import io.github.imtotem.shortly.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findAllByUserId(long id);
}

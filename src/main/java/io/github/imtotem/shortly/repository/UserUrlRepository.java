package io.github.imtotem.shortly.repository;

import io.github.imtotem.shortly.domain.UserUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserUrlRepository extends JpaRepository<UserUrl, Long> {
    List<UserUrl> findAllByUser_Email(String email);

    Optional<UserUrl> findByUser_EmailAndUrl_OriginUrl(String email, String url);

    List<UserUrl> findAllByUser_EmailAndDeletable(String email, boolean deletable);

    void deleteAllByUser_Email(String email);
}

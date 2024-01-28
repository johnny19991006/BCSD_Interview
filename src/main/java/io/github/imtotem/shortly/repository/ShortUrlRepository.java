package io.github.imtotem.shortly.repository;

import io.github.imtotem.shortly.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByOriginUrl(String url);

}

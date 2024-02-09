package io.github.imtotem.shortly.repository;

import io.github.imtotem.shortly.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    void deleteByEmail(String email);
}

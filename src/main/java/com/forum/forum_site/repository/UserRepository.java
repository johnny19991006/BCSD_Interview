package com.forum.forum_site.repository;

import com.forum.forum_site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

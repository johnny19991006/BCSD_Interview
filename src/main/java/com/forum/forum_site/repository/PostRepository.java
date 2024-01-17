package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

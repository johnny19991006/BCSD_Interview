package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Likes;
import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LikeRepository extends JpaRepository<Likes, Integer> {
    Optional<Likes> findByUserAndPost(User user, Post post);
}

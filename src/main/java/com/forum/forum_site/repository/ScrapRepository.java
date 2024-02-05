package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Likes;
import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.Scrap;
import com.forum.forum_site.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScrapRepository extends JpaRepository<Scrap, Integer> {
    Optional<Scrap> findByAuthorAndPost(User user, Post post);
    List<Scrap> findAllByAuthor(User user);
}

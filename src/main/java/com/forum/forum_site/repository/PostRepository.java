package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer>, CustomPostRepository {
    // entitygraph 어노테이션
    // JPA에서 엔티티(객체)와 그와 연관된 다른 엔티티들을 로딩하는 방법을 지정함
    // 필요한 연관 엔티티를 함께 로딩하도록 JPA에 지시하는 개념
    @EntityGraph(attributePaths = {"author"})
    Optional<Post> findWithAuthorById(int id);

    // isHot이 true인 Post 엔티티를 Page 객체로 반환
    Page<Post> findByIsHotTrue(Pageable pageable);
}

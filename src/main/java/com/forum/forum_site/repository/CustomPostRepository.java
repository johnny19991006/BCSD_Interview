package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.searchcond.SearchPostCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomPostRepository {
    Page<Post> search(SearchPostCondition searchPostCondition, Pageable pageable);
}

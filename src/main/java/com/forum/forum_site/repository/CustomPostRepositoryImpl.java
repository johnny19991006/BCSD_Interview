package com.forum.forum_site.repository;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.searchcond.SearchPostCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.forum.forum_site.domain.QPost.post;
import static com.forum.forum_site.domain.QUser.user;

@Repository
public class CustomPostRepositoryImpl implements CustomPostRepository{
    private final JPAQueryFactory query;
    public CustomPostRepositoryImpl(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Post> search(SearchPostCondition searchPostCondition, Pageable pageable) {
        List<Post> content = query.selectFrom(post)
                .where(
                        contentHasStr(searchPostCondition.getContent()),
                                titleHasStr(searchPostCondition.getTitle())
                ).leftJoin(post.author, user).fetchJoin()
                .orderBy(post.created_at.desc()) // 최신 날짜부터
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .fetch(); // count 쿼리 발생x

        JPAQuery<Post> countQuery = query.selectFrom(post).where(
                contentHasStr(searchPostCondition.getContent()), titleHasStr(searchPostCondition.getTitle()));

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
    }

    private BooleanExpression contentHasStr(String content) {
        return StringUtils.hasLength(content) ? post.content.contains(content) : null;
    }

    private BooleanExpression titleHasStr(String title) {
        return StringUtils.hasLength(title) ? post.title.contains(title) : null;
    }
}

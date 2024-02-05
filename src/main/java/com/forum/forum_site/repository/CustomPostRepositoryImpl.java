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
    // JPAQueryFactory를 사용하여 Querydsl 쿼리를 생성
    // JPAQueryFactory는 EntityManager를 기반으로 작동함 // 데이터베이스 쿼리를 실행
    private final JPAQueryFactory query;

    // 생성자를 통해 EntityManager를 주입받아 JPAQueryFactory를 초기화
    public CustomPostRepositoryImpl(EntityManager em) {
        query = new JPAQueryFactory(em);
    }

    @Override
    public Page<Post> search(SearchPostCondition searchPostCondition, Pageable pageable) {
        // 본문 내용과 제목을 기준으로 동적 쿼리를 구성하여 Post 엔티티를 검색
        List<Post> content = query.selectFrom(post)
                .where(
                        // 검색 조건에 맞는 게시물을 조회
                        // contentHasStr과 titleHasStr 메소드를 통해 동적으로 조건을 생성
                        contentHasStr(searchPostCondition.getContent()),
                        titleHasStr(searchPostCondition.getTitle())
                ).leftJoin(post.author, user).fetchJoin() // 작성자 정보를 함께 조회하기 위해 왼쪽 조인을 사용하고 fetch join으로 즉시 로딩
                .orderBy(post.created_at.desc()) // 게시글을 최신 날짜부터 정렬
                .offset(pageable.getOffset()).limit(pageable.getPageSize()) // 페이지네이션을 위한 오프셋과 리미트를 설정
                .fetch(); // 쿼리 결과를 리스트로 가져옴

        // 전체 검색 결과의 수를 구하기 위한 카운트 쿼리를 생성
        // 총 페이지 수를 계산 하는 데 사용
        JPAQuery<Post> countQuery = query.selectFrom(post).where(
                contentHasStr(searchPostCondition.getContent()), titleHasStr(searchPostCondition.getTitle()));

        // Page<Post> 객체를 생성
        // 이 메소드는 실제 페이지 컨텐츠와 총 결과 수를 기반으로 Page 객체를 구성
        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetch().size());
    }

    // 주어진 내용 문자열이 포함되는지 여부에 따라 BooleanExpression을 반환하는 헬퍼 메소드
    // StringUtils.hasLength를 사용하여 문자열이 비어있지 않은지 확인
    private BooleanExpression contentHasStr(String content) {
        return StringUtils.hasLength(content) ? post.content.contains(content) : null;
    }

    // 주어진 제목 문자열이 포함되는지 여부에 따라 BooleanExpression을 반환하는 헬퍼 메서드
    // StringUtils.hasLength를 사용하여 문자열이 비어있지 않은지 확인
    private BooleanExpression titleHasStr(String title) {
        return StringUtils.hasLength(title) ? post.title.contains(title) : null;
    }
}
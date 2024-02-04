package com.forum.forum_site.service.like;

import com.forum.forum_site.domain.Likes;
import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.exception.LikeException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.repository.LikeRepository;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl extends BaseService implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    // transactional
    // 이 어노테이션이 붙으면 해당 메서드가 실행되는 동안 시작된 모든 데이터 베이스 연산을 하나로 묶어준다
    // 엔티티의 상태 변화를 자동으로 감지 해 데이터 베이스에 자동으로 반영해준다
    // 추가로 일관성 보장 및 자동 롤백, 성능 최적화도 해준다
    @Transactional
    @Override
    public void insertLike(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        // 이미 좋아요 되어 있으면 에러
        if(likeRepository.findByAuthorAndPost(currentUser, post).isPresent()) {
            throw new LikeException(LikeException.Type.ALREADY_PRESS_LIKE);
        }

        post.insertLike();

        Likes like = Likes.builder()
                .post(post)
                .user(currentUser)
                .build();

        likeRepository.save(like);
    }

    @Transactional
    @Override
    public void deleteLike(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        Likes like = likeRepository.findByAuthorAndPost(currentUser, post)
                .orElseThrow(() -> new LikeException(LikeException.Type.LIKE_NOT_FOUND));

        post.deleteLike();

        likeRepository.delete(like);
    }
}

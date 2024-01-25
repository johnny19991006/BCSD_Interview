package com.forum.forum_site.service;

import com.forum.forum_site.domain.Likes;
import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.exception.LikeException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.exception.UserException;
import com.forum.forum_site.repository.LikeRepository;
import com.forum.forum_site.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    public void insertLike(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        // 이미 좋아요 되어 있으면 에러
        if(likeRepository.findByUserAndPost(currentUser, post).isPresent()) {
            throw new LikeException(LikeException.Type.ALREADY_PRESS_LIKE);
        }

        post.insertLike();

        Likes like = Likes.builder()
                .post(post)
                .user(currentUser)
                .build();

        likeRepository.save(like);
    }

    @Override
    public void deleteLike(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        Likes like = likeRepository.findByUserAndPost(currentUser, post)
                .orElseThrow(() -> new LikeException(LikeException.Type.LIKE_NOT_FOUND));

        post.deleteLike();

        likeRepository.delete(like);
    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new UserException(UserException.Type.NOT_FOUND_MEMBER);
    }
}

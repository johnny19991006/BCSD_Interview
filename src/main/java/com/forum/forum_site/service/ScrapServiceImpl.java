package com.forum.forum_site.service;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.Scrap;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.exception.LikeException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.exception.ScrapException;
import com.forum.forum_site.exception.UserException;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl extends BaseService implements ScrapService{
    private final ScrapRepository scrapRepository;
    private final PostRepository postRepository;

    @Override
    public void insertScrap(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        // 이미 스크랩 되어 있으면 에러
        if(scrapRepository.findByAuthorAndPost(currentUser, post).isPresent()) {
            throw new ScrapException(ScrapException.Type.ALREADY_EXIST_SCRAP);
        }

        post.scrapUser(currentUser);

        Scrap scrap = Scrap.builder()
                .post(post)
                .user(currentUser)
                        .build();

        scrapRepository.save(scrap);
    }

    @Override
    public void deleteScrap(Integer postId) {
        User currentUser = getCurrentAuthenticatedUser();
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        Scrap scrap = scrapRepository.findByAuthorAndPost(currentUser, post)
                .orElseThrow(() -> new ScrapException(ScrapException.Type.SCRAP_NOT_FOUND));

        scrapRepository.delete(scrap);
    }
}

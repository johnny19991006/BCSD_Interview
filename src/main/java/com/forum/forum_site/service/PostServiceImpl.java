package com.forum.forum_site.service;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.processing.FilerException;

@Service
@RequiredArgsConstructor
@Transactional // 데이터들의 ACID를 보장해줌
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    // private final FileService fileService;

    @Override
    public void savePost(SavePostDto savePostDto) throws FilerException {
        Post post = savePostDto.toEntity();

        // 익명 사용자로 게시글 작성
        post.confirmAuthor(null);

//        savePostDto.uploadFile().forEach(
//                file -> post.updateFilePath(fileService.save(file))
//        );

        postRepository.save(post);
    }

    @Override
    public void updatePost(Integer id, UpdatePostDto updatePostDto) {

    }

    @Override
    public void deletePost(Integer id) {

    }
}

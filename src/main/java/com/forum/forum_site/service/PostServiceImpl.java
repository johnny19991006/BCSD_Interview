package com.forum.forum_site.service;

import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.processing.FilerException;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public void savePost(SavePostDto savePostDto) throws FilerException {

    }

    @Override
    public void updatePost(Integer id, UpdatePostDto updatePostDto) {

    }

    @Override
    public void deletePost(Integer id) {

    }
}

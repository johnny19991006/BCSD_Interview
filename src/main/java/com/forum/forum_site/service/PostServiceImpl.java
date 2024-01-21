package com.forum.forum_site.service;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.exception.FileException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.exception.UserException;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.processing.FilerException;
import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional // 데이터들의 ACID를 보장해줌
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Override
    public void savePost(SavePostDto savePostDto) throws FileException {
        User currentUser = getCurrentAuthenticatedUser();

        Post post = savePostDto.toEntity();
        post.confirmAuthor(userRepository.findById(currentUser.getUser_id())
                .orElseThrow(() -> new UserException(UserException.Type.NOT_FOUND_MEMBER)));

        if (savePostDto.uploadFile() != null) {
            savePostDto.uploadFile().forEach(file -> {
                String filePath = null;
                try {
                    filePath = fileService.save(file);
                } catch (IOException e) {
                    throw new FileException(FileException.Type.FILE_CAN_NOT_UPLOAD);
                }
                post.updateFilePath(filePath);
            });
        }

        postRepository.save(post);
    }

    @Override
    public void updatePost(Integer id, UpdatePostDto updatePostDto) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_POUND));
        checkAuthority(post, PostException.Type.NOT_AUTHORITY_UPDATE_POST);

        updatePostDto.title().ifPresent(post::updateTitle);
        updatePostDto.title().ifPresent(post::updateContent);

        // 기존에 업로드 된 파일이 있으면 삭제
        if(post.getFilePath() !=null){
            fileService.delete(post.getFilePath());//기존에 올린 파일 지우기
        }

        // 새로운 파일들을 업로드하고 저장
        if (updatePostDto.uploadFiles() != null && !updatePostDto.uploadFiles().isEmpty()) {
            for (MultipartFile file : updatePostDto.uploadFiles()) {
                String filePath;
                try {
                    filePath = fileService.save(file);
                } catch (IOException e) {
                    throw new FileException(FileException.Type.FILE_CAN_NOT_UPLOAD);
                }
                post.updateFilePath(filePath); // 다중 파일 처리를 위해 로직 수정 필요
            }
        }


    }

    @Override
    public void deletePost(Integer id) {

    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new UserException(UserException.Type.NOT_FOUND_MEMBER);
    }

    private void checkAuthority(Post post, PostException.Type postExceptionType) {
        User currentUser = getCurrentAuthenticatedUser();
        if(!post.getAuthor().getUsername().equals(currentUser.getUsername()))
            throw new PostException(postExceptionType);
    }

}

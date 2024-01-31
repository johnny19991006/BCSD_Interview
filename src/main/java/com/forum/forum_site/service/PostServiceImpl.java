package com.forum.forum_site.service;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.PostInfoDto;
import com.forum.forum_site.dto.PostPagingDto;
import com.forum.forum_site.dto.SavePostDto;
import com.forum.forum_site.dto.UpdatePostDto;
import com.forum.forum_site.exception.FileException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.exception.UserException;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.repository.UserRepository;
import com.forum.forum_site.searchcond.SearchPostCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional // 데이터들의 ACID를 보장해줌
public class PostServiceImpl extends BaseService implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final FileService fileService;

    @Override
    public void savePost(SavePostDto savePostDto) throws FileException {
        User currentUser = getCurrentAuthenticatedUser();

        Post post = savePostDto.toEntity();
        post.confirmAuthor(currentUser);

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
                new PostException(PostException.Type.POST_NOT_FOUND));
        checkAuthority(post, PostException.Type.NOT_AUTHORITY_UPDATE_POST);

        updatePostDto.title().ifPresent(post::updateTitle);
        updatePostDto.content().ifPresent(post::updateContent);

        // 기존에 업로드 된 파일이 있으면 삭제
        if(post.getFilepath() !=null){
            fileService.delete(post.getFilepath());//기존에 올린 파일 지우기
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
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostException(PostException.Type.POST_NOT_FOUND));

        checkAuthority(post, PostException.Type.NOT_AUTHORITY_UPDATE_POST);

        //기존에 올린 파일 지우기
        if(post.getFilepath() !=null){
            fileService.delete(post.getFilepath());
        }

        postRepository.delete(post);
    }

    @Override
    public PostInfoDto getPostInfo(Integer id) {
        return new PostInfoDto(postRepository.findWithAuthorById(id)
                .orElseThrow(() -> new PostException(PostException.Type.POST_NOT_FOUND)));
    }

    @Override
    public PostPagingDto getPostList(Pageable pageable, SearchPostCondition searchPostCondition) {
        return new PostPagingDto(postRepository.search(searchPostCondition, pageable));
    }

    // 권한 체크하기
    private void checkAuthority(Post post, PostException.Type postExceptionType) {
        User currentUser = getCurrentAuthenticatedUser();
        if(!post.getAuthor().getUsername().equals(currentUser.getUsername()))
            throw new PostException(postExceptionType);
    }

}

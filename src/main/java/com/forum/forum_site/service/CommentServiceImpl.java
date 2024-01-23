package com.forum.forum_site.service;

import com.forum.forum_site.domain.Comment;
import com.forum.forum_site.domain.Post;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.SaveCommentDto;
import com.forum.forum_site.dto.UpdateCommentDto;
import com.forum.forum_site.exception.CommentException;
import com.forum.forum_site.exception.PostException;
import com.forum.forum_site.exception.UserException;
import com.forum.forum_site.repository.CommentRepository;
import com.forum.forum_site.repository.PostRepository;
import com.forum.forum_site.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void saveComment(Integer postId, SaveCommentDto saveCommentDto) {
        Comment comment = saveCommentDto.toEntity();
        User currentUser = getCurrentAuthenticatedUser();

        comment.confirmAuthor(userRepository.findByUsername(currentUser.getUsername()).orElseThrow(()->new UserException(UserException.Type.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostException.Type.POST_NOT_FOUND)));

        commentRepository.save(comment);

    }

    @Override
    public void saveReComment(Integer postId, Integer parentId, SaveCommentDto saveCommentDto) {
        Comment comment = saveCommentDto.toEntity();
        User currentUser = getCurrentAuthenticatedUser();

        comment.confirmAuthor(userRepository.findByUsername(currentUser.getUsername()).orElseThrow(() -> new UserException(UserException.Type.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostException.Type.POST_NOT_FOUND)));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() -> new CommentException(CommentException.Type.NOT_FOUND_COMMENT)));

    }

    @Override
    public void updateComment(Integer id, UpdateCommentDto updateCommentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentException.Type.NOT_FOUND_COMMENT));
        User currentUser = getCurrentAuthenticatedUser();

        if(!comment.getAuthor().getUsername().equals(currentUser.getUsername())) {
            throw new CommentException(CommentException.Type.NOT_AUTHORITY_UPDATE_COMMENT);
        }

        updateCommentDto.content().ifPresent(comment::updateContent);
    }

    @Override
    public void removeComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentException.Type.NOT_FOUND_COMMENT));
        User currentUser = getCurrentAuthenticatedUser();

        if(!comment.getAuthor().getUsername().equals(currentUser.getUsername())) {
            throw new CommentException(CommentException.Type.NOT_AUTHORITY_DELETE_COMMENT);
        }

        comment.remove();
        List<Comment> removalCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removalCommentList);

    }

    private User getCurrentAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User) authentication.getPrincipal();
        }

        throw new UserException(UserException.Type.NOT_FOUND_MEMBER);
    }
}

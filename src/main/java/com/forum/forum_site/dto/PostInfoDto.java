package com.forum.forum_site.dto;

import com.forum.forum_site.domain.Comment;
import com.forum.forum_site.domain.Post;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
public class PostInfoDto {
    private Integer postId;
    private String title;
    private String content;
    private String filePath;

    private UserInfoDto userDto; // 작성자 정보

    private List<CommentInfoDto> commentInfoDtos; // 댓글 정보


    public PostInfoDto(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.filePath = post.getFilepath();

        this.userDto = new UserInfoDto(post.getAuthor());


         // 댓글과 대 댓글을 그룹 지음
         // post.getCommentList()는 댓글과 대댓글이 모두 조회된다.
        Map<Comment, List<Comment>> commentListMap = post.getCommentList().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comment::getParent));



        // 댓글과 대 댓글을 통해 CommentInfoDto 생성
        commentInfoDtos = commentListMap.keySet().stream()
                .map(comment -> new CommentInfoDto(comment, commentListMap.get(comment)))
                .toList();
    }

}

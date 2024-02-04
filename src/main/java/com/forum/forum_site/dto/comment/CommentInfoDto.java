package com.forum.forum_site.dto.comment;

import com.forum.forum_site.domain.Comment;
import com.forum.forum_site.dto.user.UserInfoDto;
import lombok.Getter;

import java.util.List;

// dto에 붙어 있는 getter들은 json 직렬화와 관련이 있음
// 객체를 http응답으로 반환시 객체는 일반적으로 json 형식으로 직렬화 됨
// 이 과정은 자동으로 이루어지는데 java 객체를 json으로 변환하는 데는 java 객체의 필드 값을 읽어야 함
@Getter
public class CommentInfoDto {
    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Integer postId; // 댓글이 달린 post의 id

    private Integer commentId; // 해당 댓글의 id
    private String content; //내용 // 삭제 되면 '삭제된 댓글입니다 출력'
    private boolean isRemoved; //삭제 여부

    private UserInfoDto memberDto; //댓글 작성자에 대한 정보

    private List<ReCommentInfoDto> reCommentListDtoList; //대댓글에 대한 정보

    public CommentInfoDto(Comment comment, List<Comment> recommentList) {
        this.postId = comment.getPost().getId();
        this.commentId = comment.getComment_id();

        this.content = comment.getContent();

        if(comment.is_Removed()){
            this.content = DEFAULT_DELETE_MESSAGE;
        }

        this.isRemoved = comment.is_Removed();


        this.memberDto = new UserInfoDto(comment.getAuthor());

        this.reCommentListDtoList = recommentList.stream().map(ReCommentInfoDto::new).toList();

    }
}

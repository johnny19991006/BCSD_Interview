package com.forum.forum_site.dto.comment;

import com.forum.forum_site.domain.Comment;
import com.forum.forum_site.dto.user.UserInfoDto;
import lombok.Getter;

@Getter
public class ReCommentInfoDto {
    private final static String DEFAULT_DELETE_MESSAGE = "삭제된 댓글입니다";

    private Integer postId;
    private Integer parentId;

    private Integer reCommentId;
    private String content;
    private boolean isRemoved;

    private UserInfoDto userDto;

    public ReCommentInfoDto(Comment recomment) {
        this.postId = recomment.getPost().getId();
        this.parentId = recomment.getParent().getComment_id();
        this.reCommentId = recomment.getComment_id();
        this.content = recomment.getContent();

        if(recomment.is_Removed()) {
            this.content = DEFAULT_DELETE_MESSAGE;
        }

        this.isRemoved = recomment.is_Removed();
        this.userDto = new UserInfoDto(recomment.getAuthor());
    }
}

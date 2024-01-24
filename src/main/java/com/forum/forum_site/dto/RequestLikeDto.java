package com.forum.forum_site.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RequestLikeDto {

    private Integer memberId;
    private Integer postId;

    public RequestLikeDto(int memberId, int postId) {
        this.memberId = memberId;
        this.postId = postId;
    }
}

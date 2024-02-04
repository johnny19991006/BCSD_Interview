package com.forum.forum_site.dto.user;

import com.forum.forum_site.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoDto {
    private String username;

    @Builder
    public UserInfoDto(User member) {
        this.username = member.getUsername();
    }
}

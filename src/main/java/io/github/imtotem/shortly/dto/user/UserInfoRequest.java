package io.github.imtotem.shortly.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {
    private String email;
    private String password;
}

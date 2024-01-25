package io.github.imtotem.shortly.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoRequest {

    @Email
    @NotBlank
    private String email;

    private String password;
}

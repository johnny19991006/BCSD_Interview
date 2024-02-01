package io.github.imtotem.shortly.dto.notice;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class NoticeRequest {

    private long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @URL
    @NotBlank
    private String originUrl;

    private String description;
}

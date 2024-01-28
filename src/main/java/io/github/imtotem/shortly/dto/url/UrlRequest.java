package io.github.imtotem.shortly.dto.url;

import io.github.imtotem.shortly.domain.Url;
import io.github.imtotem.shortly.domain.User;
import io.github.imtotem.shortly.domain.UserUrl;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class UrlRequest {
    @URL
    @NotBlank
    private String originUrl;

    private String description;

    private boolean deletable;

    public UserUrl toEntity(User user) {
        return UserUrl.builder()
                .description(description)
                .deletable(deletable)
                .user(user)
                .url(Url.builder().originUrl(originUrl).build())
                .build();
    }
}

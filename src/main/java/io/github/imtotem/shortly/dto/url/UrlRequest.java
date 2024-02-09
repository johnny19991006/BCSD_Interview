package io.github.imtotem.shortly.dto.url;

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
}

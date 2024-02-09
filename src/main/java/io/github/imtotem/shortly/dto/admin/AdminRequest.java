package io.github.imtotem.shortly.dto.admin;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class AdminRequest {
    private Long id;

    @URL
    private String shortUrl;

    @URL
    private String originUrl;

}

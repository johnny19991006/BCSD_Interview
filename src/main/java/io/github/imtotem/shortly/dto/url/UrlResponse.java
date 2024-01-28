package io.github.imtotem.shortly.dto.url;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrlResponse {
    private long id;
    private String shortUrl;
    private String originUrl;
    private String description;
    private boolean isDeletable;
    private LocalDateTime createdAt;
}

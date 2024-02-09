package io.github.imtotem.shortly.dto.notice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeResponse {
    private long id;

    private String email;

    private String title;

    private String content;

    private String originUrl;

    private String description;
}

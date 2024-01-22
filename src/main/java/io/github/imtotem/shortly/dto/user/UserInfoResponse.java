package io.github.imtotem.shortly.dto.user;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse implements UserResponse {
    private long id;
    private String email;
    private LocalDateTime createdAt;
}

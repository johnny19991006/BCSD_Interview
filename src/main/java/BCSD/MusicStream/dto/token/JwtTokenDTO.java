package BCSD.MusicStream.dto.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
public class JwtTokenDTO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}

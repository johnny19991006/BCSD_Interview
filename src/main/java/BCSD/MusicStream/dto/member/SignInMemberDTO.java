package BCSD.MusicStream.dto.member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInMemberDTO {
    private String email;
    private String password;
}

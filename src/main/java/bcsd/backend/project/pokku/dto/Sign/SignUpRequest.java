package bcsd.backend.project.pokku.dto.Sign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String userName;
    private String userBirth;
    private String userTel;
    private String userEmail;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userEducation;
}

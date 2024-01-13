package bcsd.backend.project.pokku.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String UserName;
    private String UserBirth;
    private String UserTel;
    private String UserEmail;
    private String UserId;
    private String UserPassword;
    private String UserNickname;
    private String UserEducation;
}

package bcsd.backend.project.pokku.dto.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userName;
    private String userBirth;
    private String userTel;
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userEducation;
}

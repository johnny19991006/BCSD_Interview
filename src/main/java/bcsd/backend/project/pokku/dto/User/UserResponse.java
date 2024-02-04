package bcsd.backend.project.pokku.dto.User;

import bcsd.backend.project.pokku.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    private String userName;
    private String userBirth;
    private String userTel;
    private String userEmail;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userEducation;

    public UserResponse(UserInfo user){
        this.userName = user.getUserName();
        this.userBirth = user.getUserBirth();
        this.userTel = user.getUserTel();
        this.userEmail = user.getUserEmail();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickname = user.getUserNickname();
        this.userEducation = user.getUserEducation();
    }
}

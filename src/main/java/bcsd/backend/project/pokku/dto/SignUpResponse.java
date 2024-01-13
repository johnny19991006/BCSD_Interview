package bcsd.backend.project.pokku.dto;

import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private String userName;
    private String userBirth;
    private String userTel;
    private String userEmail;
    private String userId;
    private String userPassword;
    private String userNickname;
    private String userEducation;
    private String token;
    private List<Authority> authorities = new ArrayList<>();

    public SignUpResponse(UserInfo user){
        this.userName = user.getUserName();
        this.userBirth = user.getUserBirth();
        this.userTel = user.getUserTel();
        this.userEmail = user.getUserEmail();
        this.userId = user.getUserId();
        this.userPassword = user.getUserPassword();
        this.userNickname = user.getUserNickname();
        this.userEducation = user.getUserEducation();
        this.authorities = user.getAuthorities();
    }
}

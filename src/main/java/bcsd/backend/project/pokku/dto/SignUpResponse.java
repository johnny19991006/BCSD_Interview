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
    private String UserName;
    private String UserBirth;
    private String UserTel;
    private String UserEmail;
    private String UserId;
    private String UserPassword;
    private String UserNickname;
    private String UserEducation;
    private String token;
    private List<Authority> authorities = new ArrayList<>();

    public SignUpResponse(UserInfo user){
        this.UserName = user.getUserName();
        this.UserBirth = user.getUserBirth();
        this.UserTel = user.getUserTel();
        this.UserEmail = user.getUserEmail();
        this.UserId = user.getUserId();
        this.UserPassword = user.getUserPassword();
        this.UserNickname = user.getUserNickname();
        this.UserEducation = user.getUserEducation();
        this.authorities = user.getAuthorities();
    }
}

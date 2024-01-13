package bcsd.backend.project.pokku.dto;

import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponse {
    private String id;
    private String password;
    private List<Authority> roles = new ArrayList<>();
    private String token;

    public SignInResponse(UserInfo user){
        this.id = user.getUserId();
        this.password = user.getUserPassword();
        this.roles = user.getAuthorities();
    }
}
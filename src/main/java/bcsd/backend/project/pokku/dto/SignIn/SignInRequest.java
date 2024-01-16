package bcsd.backend.project.pokku.dto.SignIn;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
    private String userId;
    private String userPassword;
}
package bcsd.backend.project.pokku.dto.Sign;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignOutRequest {
    private String userId;
    private String token;
}

package bcsd.backend.project.pokku.service.SignIn;

import bcsd.backend.project.pokku.dto.SignIn.SignInRequest;
import bcsd.backend.project.pokku.dto.SignIn.SignInResponse;

public interface SignInService {
    public SignInResponse login(SignInRequest request) throws Exception;
}

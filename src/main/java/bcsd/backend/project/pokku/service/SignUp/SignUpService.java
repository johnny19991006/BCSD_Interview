package bcsd.backend.project.pokku.service.SignUp;

import bcsd.backend.project.pokku.dto.SignUp.SignUpRequest;

public interface SignUpService {
    public boolean register(SignUpRequest request) throws Exception;
}

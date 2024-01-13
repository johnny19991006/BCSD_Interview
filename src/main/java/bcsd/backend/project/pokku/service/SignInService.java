package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.SignInRequest;
import bcsd.backend.project.pokku.dto.SignInResponse;

public interface SignInService {
    public SignInResponse login(SignInRequest request) throws Exception;
}

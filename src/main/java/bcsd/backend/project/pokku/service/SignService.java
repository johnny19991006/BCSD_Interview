package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.Sign.SignInRequest;
import bcsd.backend.project.pokku.dto.Sign.SignInResponse;
import bcsd.backend.project.pokku.dto.Sign.SignOutRequest;
import bcsd.backend.project.pokku.dto.Sign.SignUpRequest;

public interface SignService {
    public SignInResponse login(SignInRequest request) throws Exception;
    public boolean logout(SignOutRequest request) throws Exception;
    public boolean register(SignUpRequest request) throws Exception;
}

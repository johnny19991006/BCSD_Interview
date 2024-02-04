package bcsd.backend.project.pokku.service.Sign;

import bcsd.backend.project.pokku.dto.Sign.SignInRequest;
import bcsd.backend.project.pokku.dto.Sign.SignInResponse;
import bcsd.backend.project.pokku.dto.Sign.SignOutRequest;
import bcsd.backend.project.pokku.dto.Sign.SignUpRequest;

public interface SignService {
    public SignInResponse login(SignInRequest request) throws RuntimeException;
    public boolean logout(SignOutRequest request) throws RuntimeException;
    public boolean register(SignUpRequest request) throws RuntimeException;
}

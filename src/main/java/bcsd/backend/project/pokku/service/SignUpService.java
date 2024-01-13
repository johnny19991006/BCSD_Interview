package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.SignUpRequest;

public interface SignUpService {
    public boolean register(SignUpRequest request) throws Exception;
}

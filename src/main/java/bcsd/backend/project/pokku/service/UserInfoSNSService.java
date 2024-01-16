package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.UserInfoSNSRequest;

public interface UserInfoSNSService {
    public boolean UpdateSNS(UserInfoSNSRequest request) throws Exception;
}

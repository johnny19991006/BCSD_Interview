package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.UserInfoSNSRequest;
import bcsd.backend.project.pokku.dto.UserInfoSNSResponse;

public interface UserInfoSNSService {
    public UserInfoSNSResponse findSNS(UserInfoSNSRequest request) throws Exception;
    public boolean UpdateSNS(UserInfoSNSRequest request) throws Exception;
}

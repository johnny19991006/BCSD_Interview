package bcsd.backend.project.pokku.service.UserInfoSNS;

import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSRequest;
import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSResponse;

public interface UserInfoSNSService {
    public UserInfoSNSResponse findSNS(UserInfoSNSRequest request) throws Exception;
    public boolean UpdateSNS(UserInfoSNSRequest request) throws Exception;
}

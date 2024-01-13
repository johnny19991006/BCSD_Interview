package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.UserRequest;
import bcsd.backend.project.pokku.dto.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse findUsers(UserRequest request) throws Exception;
    public boolean DeleteUsers(UserRequest request) throws Exception;
    public boolean UpdateUsers(UserRequest request) throws Exception;
}

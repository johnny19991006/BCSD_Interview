package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dto.User.UserDFRequest;
import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;

public interface UserService {
    public UserResponse findUsers(UserDFRequest request) throws Exception;
    public boolean DeleteUsers(UserDFRequest request) throws Exception;
    public boolean UpdateUsers(UserRequest request) throws Exception;
}

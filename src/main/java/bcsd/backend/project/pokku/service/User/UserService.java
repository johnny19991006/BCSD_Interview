package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;

public interface UserService {
    public UserResponse findUsers(String userId) throws Exception;
    public boolean DeleteUsers(String userId) throws Exception;
    public boolean UpdateUsers(String userId, UserRequest request) throws Exception;
}

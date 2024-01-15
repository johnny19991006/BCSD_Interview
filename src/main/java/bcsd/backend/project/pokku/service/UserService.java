package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dto.UserDFRequest;
import bcsd.backend.project.pokku.dto.UserRequest;
import bcsd.backend.project.pokku.dto.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse findUsers(UserDFRequest request) throws Exception;
    public boolean DeleteUsers(UserDFRequest request) throws Exception;
    public boolean UpdateUsers(UserRequest request) throws Exception;
}

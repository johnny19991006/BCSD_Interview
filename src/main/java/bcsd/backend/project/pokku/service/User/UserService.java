package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSRequest;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSResponse;

public interface UserService {
    public UserResponse findUsers(String userId) throws RuntimeException;
    public boolean DeleteUsers(String userId) throws RuntimeException;
    public boolean UpdateUsers(String userId, UserRequest request) throws RuntimeException;

    public UserSNSResponse findBlog(String userId) throws RuntimeException;
    public UserSNSResponse findGithub(String userId) throws RuntimeException;
    public UserSNSResponse findInstagram(String userId) throws RuntimeException;

    public Boolean addBlog(String userId, UserSNSRequest request) throws RuntimeException;
    public Boolean addGithub(String userId, UserSNSRequest request) throws RuntimeException;
    public Boolean addInstagram(String userId, UserSNSRequest request) throws RuntimeException;

    public Boolean updateBlog(String userId, UserSNSRequest request) throws RuntimeException;
    public Boolean updateGithub(String userId, UserSNSRequest request) throws RuntimeException;
    public Boolean updateInstagram(String userId, UserSNSRequest request) throws RuntimeException;

    public Boolean deleteBlog(String userId) throws RuntimeException;
    public Boolean deleteGithub(String userId) throws RuntimeException;
    public Boolean deleteInstagram(String userId) throws RuntimeException;
}

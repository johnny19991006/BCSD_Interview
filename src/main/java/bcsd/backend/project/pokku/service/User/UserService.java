package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSRequest;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSResponse;

public interface UserService {
    public UserResponse findUsers(String userId) throws Exception;
    public boolean DeleteUsers(String userId) throws Exception;
    public boolean UpdateUsers(String userId, UserRequest request) throws Exception;

    public UserSNSResponse findBlog(String userId) throws Exception;
    public UserSNSResponse findGithub(String userId) throws Exception;
    public UserSNSResponse findInstagram(String userId) throws Exception;

    public Boolean addBlog(String userId, UserSNSRequest request) throws Exception;
    public Boolean addGithub(String userId, UserSNSRequest request) throws Exception;
    public Boolean addInstagram(String userId, UserSNSRequest request) throws Exception;

    public Boolean updateBlog(String userId, UserSNSRequest request) throws Exception;
    public Boolean updateGithub(String userId, UserSNSRequest request) throws Exception;
    public Boolean updateInstagram(String userId, UserSNSRequest request) throws Exception;

    public Boolean deleteBlog(String userId) throws Exception;
    public Boolean deleteGithub(String userId) throws Exception;
    public Boolean deleteInstagram(String userId) throws Exception;
}

package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dao.UserInfoBlogRepository;
import bcsd.backend.project.pokku.dao.UserInfoGithubRepository;
import bcsd.backend.project.pokku.dao.UserInfoInstagramRepository;
import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoBlog;
import bcsd.backend.project.pokku.domain.UserInfoGithub;
import bcsd.backend.project.pokku.domain.UserInfoInstagram;
import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSRequest;
import bcsd.backend.project.pokku.dto.UserSNS.UserSNSResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoGithubRepository userInfoGithubRepository;
    private final UserInfoBlogRepository userInfoBlogRepository;
    private final UserInfoInstagramRepository userInfoInstagramRepository;

    @Override
    public UserResponse findUsers(String userId) throws Exception{
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        return new UserResponse(userInfo);
    }

    @Override
    public boolean DeleteUsers(String userId) throws Exception{

        try {
            userInfoRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public boolean UpdateUsers(String userId, UserRequest request) throws Exception{
        try {
            UserInfo userInfo = UserInfo.builder()
                    .userId(userId)
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .userName(request.getUserName())
                    .userNickname(request.getUserNickname())
                    .userBirth(request.getUserBirth())
                    .userEmail(request.getUserEmail())
                    .userTel(request.getUserTel())
                    .userEducation(request.getUserEducation())
                    .build();

            userInfoRepository.save(userInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public UserSNSResponse findBlog(String userId) throws Exception {
        UserInfoBlog userInfoBlog = userInfoBlogRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("존재하지 않는 사용자 입니다."));

        return new UserSNSResponse(userInfoBlog.getUserBlog());
    }

    @Override
    public UserSNSResponse findGithub(String userId) throws Exception {
        UserInfoGithub userInfoGithub = userInfoGithubRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("존재하지 않는 사용자 입니다."));

        return new UserSNSResponse(userInfoGithub.getUserGithub());
    }

    @Override
    public UserSNSResponse findInstagram(String userId) throws Exception {
        UserInfoInstagram userInfoInstagram = userInfoInstagramRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new BadCredentialsException("존재하지 않는 사용자 입니다."));

        return new UserSNSResponse(userInfoInstagram.getUserInstagram());
    }

    @Override
    public Boolean updateBlog(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoBlog userInfoBlog = UserInfoBlog.builder()
                    .userBlog(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();
            userInfoBlogRepository.save(userInfoBlog);

        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new Exception("잘못된 요청입니다.");
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateGithub(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoGithub userInfoGithub = UserInfoGithub.builder()
                    .userGithub(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();
            userInfoGithubRepository.save(userInfoGithub);

        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new Exception("잘못된 요청입니다.");
            return false;
        }
        return true;
    }

    @Override
    public Boolean updateInstagram(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoInstagram userInfoInstagram = UserInfoInstagram.builder()
                    .userInstagram(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();
            userInfoInstagramRepository.save(userInfoInstagram);

        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new Exception("잘못된 요청입니다.");
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteBlog(String userId) throws Exception {
        try {
            userInfoBlogRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public Boolean deleteGithub(String userId) throws Exception {
        try {
            userInfoGithubRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public Boolean deleteInstagram(String userId) throws Exception {
        try {
            userInfoInstagramRepository.deleteById(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public Boolean addBlog(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoBlog userInfoBlog = UserInfoBlog.builder()
                    .userBlog(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();

            userInfoBlogRepository.save(userInfoBlog);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public Boolean addGithub(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoGithub userInfoGithub = UserInfoGithub.builder()
                    .userGithub(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();

            userInfoGithubRepository.save(userInfoGithub);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }

    @Override
    public Boolean addInstagram(String userId, UserSNSRequest request) throws Exception {
        try {
            UserInfoInstagram userInfoInstagram = UserInfoInstagram.builder()
                    .userInstagram(request.getSnsName())
                    .userInfo(UserInfo.builder().userId(userId).build())
                    .build();

            userInfoInstagramRepository.save(userInfoInstagram);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}

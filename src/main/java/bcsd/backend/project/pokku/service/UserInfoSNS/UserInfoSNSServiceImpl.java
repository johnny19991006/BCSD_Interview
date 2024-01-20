package bcsd.backend.project.pokku.service.UserInfoSNS;

import bcsd.backend.project.pokku.dao.UserInfoBlogRepository;
import bcsd.backend.project.pokku.dao.UserInfoGithubRepository;
import bcsd.backend.project.pokku.dao.UserInfoInstagramRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserInfoBlog;
import bcsd.backend.project.pokku.domain.UserInfoGithub;
import bcsd.backend.project.pokku.domain.UserInfoInstagram;
import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSRequest;
import bcsd.backend.project.pokku.dto.UserInfoSNS.UserInfoSNSResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoSNSServiceImpl implements UserInfoSNSService{

    private final UserInfoBlogRepository userInfoBlogRepository;
    private final UserInfoInstagramRepository userInfoInstagramRepository;
    private final UserInfoGithubRepository userInfoGithubRepository;

    @Override
    public UserInfoSNSResponse findSNS(UserInfoSNSRequest request) throws Exception{
        UserInfoGithub userInfoGithub = userInfoGithubRepository.findByUserId(UserInfo.builder().userId(request.getUserId()).build())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));
        UserInfoInstagram userInfoInstagram = userInfoInstagramRepository.findByUserId(UserInfo.builder().userId(request.getUserId()).build())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));
        UserInfoBlog userInfoBlog = userInfoBlogRepository.findByUserId(UserInfo.builder().userId(request.getUserId()).build())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));

        return UserInfoSNSResponse.builder()
                .userId(request.getUserId())
                .userInstagram(userInfoInstagram.getUserInstagram())
                .userBlog(userInfoBlog.getUserBlog())
                .userGithub(userInfoGithub.getUserGithub())
                .build();
    }

    @Override
    public boolean UpdateSNS(UserInfoSNSRequest request) throws Exception{
        try {
            UserInfoGithub userInfoGithub = UserInfoGithub.builder()
                    .userGithub(request.getUserGithub())
                    .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                    .build();

            UserInfoInstagram userInfoInstagram = UserInfoInstagram.builder()
                    .userInstagram(request.getUserInstagram())
                    .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                    .build();

            UserInfoBlog userInfoBlog = UserInfoBlog.builder()
                    .userBlog(request.getUserBlog())
                    .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                    .build();

           userInfoBlogRepository.save(userInfoBlog);
           userInfoInstagramRepository.save(userInfoInstagram);
           userInfoGithubRepository.save(userInfoGithub);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}

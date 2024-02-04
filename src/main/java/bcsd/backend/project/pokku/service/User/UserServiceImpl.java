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
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.ResCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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
    public UserResponse findUsers(String userId) throws RuntimeException{
        UserInfo userInfo = userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        return new UserResponse(userInfo);
    }

    @Override
    public boolean DeleteUsers(String userId) throws RuntimeException{
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoRepository.deleteById(userId);

        return true;
    }

    @Override
    public boolean UpdateUsers(String userId, UserRequest request) throws RuntimeException{
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

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

        return true;
    }

    @Override
    public UserSNSResponse findBlog(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        UserInfoBlog userInfoBlog = userInfoBlogRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("사용자의 Blog정보가 존재하지 않습니다.", userId, ResCode.NO_SUCH_DATA.value()));

        return new UserSNSResponse(userInfoBlog.getUserBlog());
    }

    @Override
    public UserSNSResponse findGithub(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        UserInfoGithub userInfoGithub = userInfoGithubRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("사용자의 Github 정보가 존재하지 않습니다.", userId, ResCode.NO_SUCH_DATA.value()));

        return new UserSNSResponse(userInfoGithub.getUserGithub());
    }

    @Override
    public UserSNSResponse findInstagram(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));
        UserInfoInstagram userInfoInstagram = userInfoInstagramRepository.findByUserId(UserInfo.builder().userId(userId).build())
                .orElseThrow(() -> new NoSuchDataException("사용자의 Instagram정보가 존재하지 않습니다..", userId, ResCode.NO_SUCH_DATA.value()));

        return new UserSNSResponse(userInfoInstagram.getUserInstagram());
    }

    @Override
    public Boolean updateBlog(String userId, UserSNSRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoBlogRepository.updateByUserId(UserInfo.builder().userId(userId).build(), request.getSnsName());

        return true;
    }

    @Override
    public Boolean updateGithub(String userId, UserSNSRequest request) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoGithubRepository.updateByUserId(UserInfo.builder().userId(userId).build(), request.getSnsName());

        return true;
    }

    @Override
    public Boolean updateInstagram(String userId, UserSNSRequest request) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoInstagramRepository.updateByUserId(UserInfo.builder().userId(userId).build(), request.getSnsName());

        return true;
    }

    @Override
    public Boolean deleteBlog(String userId) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoBlogRepository.deleteByUserId(UserInfo.builder().userId(userId).build());

        return true;
    }

    @Override
    public Boolean deleteGithub(String userId) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoGithubRepository.deleteByUserId(UserInfo.builder().userId(userId).build());

        return true;
    }

    @Override
    public Boolean deleteInstagram(String userId) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        userInfoInstagramRepository.deleteByUserId(UserInfo.builder().userId(userId).build());

        return true;
    }

    @Override
    public Boolean addBlog(String userId, UserSNSRequest request) throws RuntimeException {
        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        if(userInfoBlogRepository.countById(request.getSnsName()) != 0){
            throw new DuplicateKeyException("이미 존재하는 Blog정보 입니다.", request.getSnsName(), ResCode.DUPLICATE_KEY.value());
        }

        UserInfoBlog userInfoBlog = UserInfoBlog.builder()
                .userBlog(request.getSnsName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build();

        userInfoBlogRepository.save(userInfoBlog);

        return true;
    }

    @Override
    public Boolean addGithub(String userId, UserSNSRequest request) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        if(userInfoGithubRepository.countById(request.getSnsName()) != 0){
            throw new DuplicateKeyException("이미 존재하는 Github정보 입니다.", request.getSnsName(), ResCode.DUPLICATE_KEY.value());
        }

        UserInfoGithub userInfoGithub = UserInfoGithub.builder()
                .userGithub(request.getSnsName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build();

        userInfoGithubRepository.save(userInfoGithub);

        return true;
    }

    @Override
    public Boolean addInstagram(String userId, UserSNSRequest request) throws RuntimeException {

        userInfoRepository.findById(userId)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userId, ResCode.NO_SUCH_DATA.value()));

        if(userInfoInstagramRepository.countById(request.getSnsName()) != 0){
            throw new DuplicateKeyException("이미 존재하는 Instagram정보 입니다.", request.getSnsName(), ResCode.DUPLICATE_KEY.value());
        }

        UserInfoInstagram userInfoInstagram = UserInfoInstagram.builder()
                .userInstagram(request.getSnsName())
                .userInfo(UserInfo.builder().userId(userId).build())
                .build();

        userInfoInstagramRepository.save(userInfoInstagram);

        return true;
    }
}

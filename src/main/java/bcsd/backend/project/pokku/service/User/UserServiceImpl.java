package bcsd.backend.project.pokku.service.User;

import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.User.UserRequest;
import bcsd.backend.project.pokku.dto.User.UserResponse;
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
}

package bcsd.backend.project.pokku.service;

import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.SignUpRequest;
import bcsd.backend.project.pokku.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean register(SignUpRequest request) throws Exception{
        try {
            UserInfo userInfo = UserInfo.builder()
                    .UserId(request.getUserId())
                    .UserPassword(passwordEncoder.encode(request.getUserPassword()))
                    .UserName(request.getUserName())
                    .UserNickname(request.getUserNickname())
                    .UserBirth(request.getUserBirth())
                    .UserEmail(request.getUserEmail())
                    .UserTel(request.getUserTel())
                    .UserEducation(request.getUserEducation())
                    .build();

            userInfo.setRoles(Collections.singletonList(Authority.builder()
                    .AuthName("ROLE_User")
                    .build()));

            userInfoRepository.save(userInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}

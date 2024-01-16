package bcsd.backend.project.pokku.service.SignUp;

import bcsd.backend.project.pokku.dao.AuthorityRepository;
import bcsd.backend.project.pokku.dao.PortfolioAboutRepository;
import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.SignUp.SignUpRequest;
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
    private final AuthorityRepository authorityRepository;
    private final PortfolioAboutRepository portfolioAboutRepository;

    @Override
    public boolean register(SignUpRequest request) throws Exception{
        try {
            UserInfo userInfo = UserInfo.builder()
                    .userId(request.getUserId())
                    .userPassword(passwordEncoder.encode(request.getUserPassword()))
                    .userName(request.getUserName())
                    .userNickname(request.getUserNickname())
                    .userBirth(request.getUserBirth())
                    .userEmail(request.getUserEmail())
                    .userTel(request.getUserTel())
                    .userEducation(request.getUserEducation())
                    .build();

            userInfo.setRoles(Collections.singletonList(Authority.builder()
                    .AuthName("ROLE_User")
                    .build()));

            userInfoRepository.save(userInfo);
            authorityRepository.save(Authority.builder()
                    .AuthName("ROLE_User")
                    .userInfo(userInfo)
                    .build());
            portfolioAboutRepository.save(PortfolioAbout.builder()
                    .userNameVisible(Boolean.FALSE)
                    .userEducationVisible(Boolean.FALSE)
                    .userEmailVisible(Boolean.FALSE)
                    .userTelVisible(Boolean.FALSE)
                    .userInfo(userInfo)
                    .build());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }
}

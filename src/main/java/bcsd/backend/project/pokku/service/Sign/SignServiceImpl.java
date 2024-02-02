package bcsd.backend.project.pokku.service.Sign;

import bcsd.backend.project.pokku.dao.AuthorityRepository;
import bcsd.backend.project.pokku.dao.PortfolioAboutRepository;
import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.domain.PortfolioAbout;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.Sign.SignInRequest;
import bcsd.backend.project.pokku.dto.Sign.SignInResponse;
import bcsd.backend.project.pokku.dto.Sign.SignOutRequest;
import bcsd.backend.project.pokku.dto.Sign.SignUpRequest;
import bcsd.backend.project.pokku.exception.DuplicateKeyException.DuplicateKeyException;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchException;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.ResCode;
import bcsd.backend.project.pokku.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class SignServiceImpl implements SignService{

    private final AuthorityRepository authorityRepository;
    private final PortfolioAboutRepository portfolioAboutRepository;
    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public SignInResponse login(SignInRequest request) throws RuntimeException{
        UserInfo userInfo = userInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", request.getUserId(), ResCode.NO_SUCH_DATA.value()));
        if(!passwordEncoder.matches(request.getUserPassword(), userInfo.getUserPassword())){
            throw new InputMismatchException("잘못된 계정 정보 입니다.", "password", ResCode.INPUT_MISMATCH.value());
        }

        return SignInResponse.builder()
                .userId(userInfo.getUserId())
                .userPassword(userInfo.getUserPassword())
                .roles(userInfo.getAuthorities())
                .token(jwtProvider.createToken(userInfo.getUserId(), userInfo.getAuthorities()))
                .build();
    }

    @Override
    public boolean register(SignUpRequest request) throws RuntimeException{
        if (userInfoRepository.countById(request.getUserId()) != 0){
            throw new DuplicateKeyException("이미 존재하는 사용자 입니다.", request.getUserId(), ResCode.DUPLICATE_KEY.value());
        }

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
//                    .AuthName("ROLE_User")
                .AuthName("ROLE_Admin")
                .build()));

        userInfoRepository.save(userInfo);
        authorityRepository.save(Authority.builder()
//                    .AuthName("ROLE_User")
                .AuthName("ROLE_Admin")
                .userInfo(userInfo)
                .build());
        portfolioAboutRepository.save(PortfolioAbout.builder()
                .userNameVisible(Boolean.FALSE)
                .userEducationVisible(Boolean.FALSE)
                .userEmailVisible(Boolean.FALSE)
                .userTelVisible(Boolean.FALSE)
                .userInfo(userInfo)
                .build());

        return true;
    }

    @Override
    public boolean logout(SignOutRequest request) throws RuntimeException{
        userInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", request.getUserId(), ResCode.NO_SUCH_DATA.value()));
        return jwtProvider.invalidateToken(request.getToken(), request.getUserId());


    }
}

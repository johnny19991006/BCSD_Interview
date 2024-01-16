package bcsd.backend.project.pokku.service.SignIn;

import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.dto.SignIn.SignInRequest;
import bcsd.backend.project.pokku.dto.SignIn.SignInResponse;
import bcsd.backend.project.pokku.security.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService{

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override
    public SignInResponse login(SignInRequest request) throws Exception{
        UserInfo userInfo = userInfoRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadCredentialsException("잘못된 계정 정보 입니다."));
        if(!passwordEncoder.matches(request.getUserPassword(), userInfo.getUserPassword())){
            throw new BadCredentialsException("잘못된 계정 정보 입니다.");
        }

        return SignInResponse.builder()
                .userId(userInfo.getUserId())
                .userPassword(userInfo.getUserPassword())
                .roles(userInfo.getAuthorities())
                .token(jwtProvider.createToken(userInfo.getUserId(), userInfo.getAuthorities()))
                .build();
    }
}

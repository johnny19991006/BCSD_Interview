package HSAnimal.demo.service;

import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.RefreshToken;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.RefreshTokenRepository;
import HSAnimal.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

//    public TokenService(TokenProvider tokenProvider, RefreshTokenService refreshTokenService, UserService userService){
//        this.tokenProvider = tokenProvider;
//        this.refreshTokenService = refreshTokenService;
//        this.userService = userService;
//    }

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        String userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userRepository.findByUserId(userId).get();

        return tokenProvider.generateToken(user, Duration.ofMinutes(1));
    }

    public String new1(String token){
        if (token != null && tokenProvider.validToken(token)) {
            // 리프레시 토큰을 사용하여 새로운 액세스 토큰 발급 시도
            String userId = tokenProvider.getUserId(token);
            String refreshToken = refreshTokenRepository.findByUserId(userId).get().getRefreshToken();
            if (refreshToken != null) {
                String newAccessToken = createNewAccessToken(refreshToken); // 리프레시 토큰을 사용하여 새로운 액세스 토큰 생성
                return newAccessToken;
            }
        }
        return null;
    }

    public void deleteRefreshToken(){

    }

    public String hello(){
        return "hello";
    }

    public String reCreateAccessToken(String token){
        String userId = tokenProvider.getUserId(token);
        String refreshToken = refreshTokenRepository.findByUserId(userId).get().getRefreshToken();
        return createNewAccessToken(refreshToken);
    }
}

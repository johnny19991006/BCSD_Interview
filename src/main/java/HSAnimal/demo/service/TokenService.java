package HSAnimal.demo.service;

import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.User;
import org.springframework.stereotype.Service;
import java.time.Duration;

@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    public TokenService(TokenProvider tokenProvider, RefreshTokenService refreshTokenService, UserService userService){
        this.tokenProvider = tokenProvider;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
    }

    public String createNewAccessToken(String refreshToken) {
        // 토큰 유효성 검사에 실패하면 예외 발생
        if(!tokenProvider.validToken(refreshToken)) {
            throw new IllegalArgumentException("Unexpected token");
        }

        String userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(1));
    }
}
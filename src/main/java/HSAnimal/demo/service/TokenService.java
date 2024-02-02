package HSAnimal.demo.service;

import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.RefreshToken;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.RefreshTokenRepository;
import HSAnimal.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenService(TokenProvider tokenProvider, UserRepository userRepository,
                        RefreshTokenRepository refreshTokenRepository){
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public String recreateAccessToken(String userId) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userId);
            String refreshToken = optionalRefreshToken
                    .orElseThrow(() -> new IllegalArgumentException("Unexpected token"))
                    .getRefreshToken();
            if (tokenProvider.validToken(refreshToken)) {
                User user = userRepository.findByUserId(userId)
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
                return tokenProvider.generateToken(user, Duration.ofMinutes(1));
            } else{
                throw new IllegalArgumentException("Unexpected token");
            }
    }

    public void deleteRefreshToken(String userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        refreshTokenRepository.delete(refreshToken);
    }


}

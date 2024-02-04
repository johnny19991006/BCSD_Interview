package HSAnimal.demo.service;

import HSAnimal.demo.DTO.CreateAccessTokenDto;
import HSAnimal.demo.configuration.JwtProperties;
import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.RefreshToken;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.exception.AccountNotFoundException;
import HSAnimal.demo.repository.RefreshTokenRepository;
import HSAnimal.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    public TokenService(TokenProvider tokenProvider, UserRepository userRepository,
                        RefreshTokenRepository refreshTokenRepository, JwtProperties jwtProperties){
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtProperties = jwtProperties;
    }

    public CreateAccessTokenDto recreateAccessToken(String userId) {
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByUserId(userId);
            String refreshToken = optionalRefreshToken
                    .orElseThrow(() -> new IllegalArgumentException("Unexpected token"))
                    .getRefreshToken();
            if (tokenProvider.validToken(refreshToken)) {
                User user = userRepository.findByUserId(userId)
                        .orElseThrow(() -> new AccountNotFoundException("User not found"));
                return createAccessToken(user);
            } else{
                throw new IllegalArgumentException("Unexpected token");
            }
    }

    public CreateAccessTokenDto createAccessToken(User user){
        return new CreateAccessTokenDto(tokenProvider
                .generateToken(user, Duration.ofMinutes(jwtProperties.getValidMinutes())));
    }

    public void saveRefreshToken(User user){
        String refreshToken = tokenProvider.generateRefreshToken(Duration.ofHours(jwtProperties.getRefreshValidHours()));
        refreshTokenRepository.findByUserId(user.getUserId())
                .ifPresentOrElse(
                        it -> it.updateRefreshToken(refreshToken),
                        () -> refreshTokenRepository.save(new RefreshToken(user.getUserId(), refreshToken))
                );
    }
    public void deleteRefreshToken(String userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFoundException("User not found"));
        refreshTokenRepository.delete(refreshToken);
    }


}

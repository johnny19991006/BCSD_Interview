package com.example.board.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.board.domain.User;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
public class JwtTokenProvider {
    private final UserRepository userRepository;

    static Long EXPIRE_TIME = 60L * 60L * 1000L; // 만료시간 1시간

    @Value("${secretKey}")
    private String secretKey;

    private Algorithm getSign() {
        return Algorithm.HMAC512(secretKey);
    }

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    public String generateJwtToken(Integer id, String email, String username) {
        Date tokenExpiration = new Date(System.currentTimeMillis() + (EXPIRE_TIME));

        return JWT.create()
                .withSubject(email)
                .withExpiresAt(tokenExpiration)
                .withClaim("id", id)
                .withClaim("email", email)
                .withClaim("username", username)
                .sign(this.getSign());
    }

    public User validToken(String jwtToken) {
        try {
            String email = JWT.require(this.getSign()).build().verify(jwtToken).getClaim("email").asString();

            if (email == null) return null;

            Date expiresAt = JWT.require(this.getSign()).acceptExpiresAt(EXPIRE_TIME).build().verify(jwtToken).getExpiresAt();
            if (!this.validExpiredTime(expiresAt)) return null;

            return userRepository.findByUserEmail(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validExpiredTime(Date expiresAt) {
        LocalDateTime localTimeExpired = expiresAt.toInstant().atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime();
        return LocalDateTime.now().isBefore(localTimeExpired);
    }
}

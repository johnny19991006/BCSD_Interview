package com.example.studyroom.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class JWTProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expired_time}")
    private Integer expiredTime;

    public String createToken(Integer schoolId) {    // schoolId로 토큰 생성

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .expiration(Date.valueOf(LocalDateTime.now().plusMinutes(expiredTime).toLocalDate()))
                .claim("schoolId", schoolId)
                .signWith(key)
                .compact();
    }

    public Integer getSchoolId(String token) {      // 토큰이 들어오면 파싱해서 schoolId값을 가져옴

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String schoolId = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("schoolId").toString();

        return Integer.parseInt(schoolId);
    }
}

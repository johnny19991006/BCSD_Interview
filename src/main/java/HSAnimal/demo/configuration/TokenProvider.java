package HSAnimal.demo.configuration;

import HSAnimal.demo.domain.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Component
@Slf4j
public class TokenProvider {
    private final JwtProperties jwtProperties;

    public TokenProvider(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(User user, Duration expiredAt){
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    public String generateRefreshToken(Duration expiredAt){
        Date now = new Date();
        return makeRefreshToken(new Date(now.getTime() + expiredAt.toMillis()));
    }

    // JWT 토큰 생성 메서드
    private String makeToken(Date expiry, User user) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .setSubject(user.getEmail())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    private String makeRefreshToken(Date expiry) {
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT 토큰 유효성 검사 메서드
    public boolean validToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
                log.info("SignatureException");
                throw new JwtException(ErrorCode.WRONG_TYPE_TOKEN.getMessage());
            } catch (MalformedJwtException e) {
                log.info("MalformedJwtException");
                throw new JwtException(ErrorCode.UNSUPPORTED_TOKEN.getMessage());
            } catch (ExpiredJwtException e) {
                log.info("ExpiredJwtException");
                throw new JwtException(ErrorCode.EXPIRED_TOKEN.getMessage());
            } catch (IllegalArgumentException e) {
                log.info("IllegalArgumentException");
                return false;
//                throw new JwtException(ErrorCode.TOKEN_NOT_FOUND.getMessage());
            }
    }

    // 토큰 기반으로 인증 정보 가져오는 메서드
    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority("USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework.security
                .core.userdetails.User(claims.getSubject(), "", authorities),
                token, authorities);
    }

    // 토큰 기반으로 사용자 ID를 가져오는 메서드
    public String getUserId(String token){
        Claims claims = getClaims(token);
        return claims.get("userId", String.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

}

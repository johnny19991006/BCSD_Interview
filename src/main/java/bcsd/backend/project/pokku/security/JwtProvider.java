package bcsd.backend.project.pokku.security;

import bcsd.backend.project.pokku.domain.Authority;
import bcsd.backend.project.pokku.exception.InputMismatchException.InputMismatchException;
import bcsd.backend.project.pokku.exception.ResCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {

    @Value("${jwt.secret.key}")
    private String salt;

    private Key secretKey;

    private final long exp = 1000L * 60 * 60;
//    private final long exp = 1L;
    private final JpaUserInfoDetailsService userInfoDetailsService;

    @PostConstruct
    protected void init(){
        secretKey = Keys.hmacShaKeyFor(salt.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String account, List<Authority> roles){

        Claims claims = Jwts.claims().setSubject(account);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userInfoDetailsService.loadUserByUsername(this.getAccount(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getAccount(String token){
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token){
        try {
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                throw new InputMismatchException("토큰 형식이 일치하지 않습니다.", "token", ResCode.INPUT_MISMATCH.value());
            } else {
                token = token.split(" ")[1].trim();
            }

            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (InputMismatchException e){
            throw new InputMismatchException(e.getMessage(), e.getHint(), e.getErrorCode());
        } catch (Exception e){
            throw e;
        }
    }

    public boolean invalidateToken(String token, String accountId){
        try {
            if (!token.substring(0, "BEARER ".length()).equalsIgnoreCase("BEARER ")) {
                throw new InputMismatchException("토큰 형식이 일치하지 않습니다.", "token", ResCode.INPUT_MISMATCH.value());
            } else {
                token = token.split(" ")[1].trim();
            }

            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);

            String tokenAccountId = claims.getBody().getSubject();
            if (!tokenAccountId.equals(accountId)) {
                throw new InputMismatchException("계정이 일치하지 않습니다.", "userId", ResCode.INPUT_MISMATCH.value());
            }

            claims.getBody().setExpiration(new Date(System.currentTimeMillis() - (1000 * 60 * 60)));

            return true;
        } catch (InputMismatchException e){
            throw new InputMismatchException(e.getMessage(), e.getHint(), e.getErrorCode());
        } catch (Exception e){
            throw e;
        }
    }

}
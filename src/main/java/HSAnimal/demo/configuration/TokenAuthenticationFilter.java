package HSAnimal.demo.configuration;

import HSAnimal.demo.exception.NotAllowedAuthorityException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    public TokenAuthenticationFilter (TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)  throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        String token = getAccessToken(authorizationHeader);

        if (tokenProvider.validToken(token)) {
            // UserDetails 미완성으로 "hyunn815"에게 "ROLE_ADMIN" 임시 부여
            if (request.getRequestURI().startsWith("/admin/**") && !tokenProvider.getUserId(token).equals("hyunn815")){
                throw new NotAllowedAuthorityException("Not allowed authority.");
            }
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}

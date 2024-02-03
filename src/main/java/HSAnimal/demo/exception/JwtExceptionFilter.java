package HSAnimal.demo.exception;

import HSAnimal.demo.enums.ErrorCode;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@NoArgsConstructor
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain)
            throws ServletException, IOException {
        try {
            filterchain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();
            if(ErrorCode.TOKEN_NOT_FOUND.getMessage().equals(message)) {
                setResponse(response, ErrorCode.TOKEN_NOT_FOUND);
            }
            //잘못된 타입의 토큰인 경우
            else if(ErrorCode.WRONG_TYPE_TOKEN.getMessage().equals(message)) {
                setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
            }
            //토큰 만료된 경우
            else if(ErrorCode.EXPIRED_TOKEN.getMessage().equals(message)) {
                setResponse(response, ErrorCode.EXPIRED_TOKEN);

            }
            //지원 되지 않는 토큰인 경우
            else if(ErrorCode.UNSUPPORTED_TOKEN.getMessage().equals(message)) {
                setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
            }
            else {
                setResponse(response, ErrorCode.ACCESS_DENIED);
            }
        }
    }

    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getCode());
        response.getWriter().print(errorCode.getMessage());
    }
}

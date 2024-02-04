package bcsd.backend.project.pokku.security;

import bcsd.backend.project.pokku.exception.NullValueException.NullValueException;
import bcsd.backend.project.pokku.exception.ResCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    public JwtAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        if (request != null) {
            Exception resolvedException = (Exception) request.getAttribute("exception");

            if (resolvedException != null) {
                resolver.resolveException(request, response, null, resolvedException);
            } else {
                resolver.resolveException(request, response, null, new NullValueException("토큰이 비어있습니다.", "token", ResCode.NULL_VALUE.value()));
            }
        } else {
            resolver.resolveException(request, response, null, new NullValueException("요청이 비어있습니다.", "request", ResCode.NULL_VALUE.value()));
        }
    }
}
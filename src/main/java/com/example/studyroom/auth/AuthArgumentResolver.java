package com.example.studyroom.auth;

import com.example.studyroom.domain.User;
import com.example.studyroom.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    public AuthArgumentResolver(UserRepository userRepository, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authorization.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest nativeRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        System.out.println(nativeRequest);
        String request = nativeRequest.getHeader("Authorization");

        if (request != null) {
            Integer schoolId = jwtProvider.getSchoolId(request);
            User user = userRepository.findBySchoolId(schoolId);

            if (user == null) {
                throw new IllegalArgumentException("존재하지 않는 사용자입니다!");
            }

            return user.getSchoolId();
        }

        throw new IllegalArgumentException("헤더가 존재하지 않습니다");
    }
}

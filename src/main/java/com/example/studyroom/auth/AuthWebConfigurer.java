package com.example.studyroom.auth;

import com.example.studyroom.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthWebConfigurer implements WebMvcConfigurer {
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    public AuthWebConfigurer(UserRepository userRepository, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new AuthArgumentResolver(userRepository, jwtProvider));
    }
}

package io.github.imtotem.shortly.security;

import io.github.imtotem.shortly.security.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;

    private final AuthenticationEntryPoint userAuthenticationEntryPoint;
    private final AccessDeniedHandler userAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(CsrfConfigurer::disable) // rest api 이므로 csrf 보안이 필요 없음. disable
            .cors(Customizer.withDefaults())

            .httpBasic(HttpBasicConfigurer::disable) // rest api 이므로 기본설정 사용안함. 기본설정은 비인증시 로그인폼 화면으로 리다이렉트 된다.
            .formLogin(FormLoginConfigurer::disable)

            // jwt token으로 인증하므로 세션은 필요없으므로 생성안함.
            .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // jwt token 필터
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling(exceptionHandling -> exceptionHandling
                    .authenticationEntryPoint(userAuthenticationEntryPoint)
                    .accessDeniedHandler(userAccessDeniedHandler)
            )

            .authorizeHttpRequests(authorize -> authorize  // 다음 리퀘스트에 대한 사용권한 체크
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
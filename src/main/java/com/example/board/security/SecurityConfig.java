package com.example.board.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .cors().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/users/{userId}/admin").hasRole("ADMIN") // 회원정보단일조회(ADMIN)
                .antMatchers(HttpMethod.GET, "/users/{userId}/general").hasRole("GENERAL") // 회원정보단일조회(GENERAL)
                .antMatchers(HttpMethod.PUT, "/users/{userId}/type").hasRole("ADMIN") // 회원정보권한수정(ADMIN)
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN") // 회원정보전체조회(ADMIN)
                .antMatchers(HttpMethod.POST, "/usertypes").hasRole("ADMIN") // 유저타입추가(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/usertypes/{usertypeId}").hasRole("ADMIN") // 유저타입삭제(ADMIN)
                .antMatchers(HttpMethod.GET, "/usertypes").hasRole("ADMIN") // 유저타입전체조회(ADMIN)
                .antMatchers(HttpMethod.POST, "/categories").hasRole("ADMIN") // 카테고리추가(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/categories/{categoryId}").hasRole("ADMIN") // 카테고리삭제(ADMIN)
                .antMatchers(HttpMethod.GET, "/categories").hasRole("ADMIN") // 카테고리전체조회(ADMIN)
                .antMatchers(HttpMethod.POST, "/hashtags").hasRole("ADMIN") // 해시태그추가(ADMIN)
                .antMatchers(HttpMethod.DELETE, "/hashtags/{hashtagId}").hasRole("ADMIN") // 해시태그삭제(ADMIN)
                .antMatchers(HttpMethod.GET, "/hashtags").hasRole("ADMIN") // 해시태그전체조회(ADMIN)
                .antMatchers(HttpMethod.GET, "/users/usertype/{userTypeId}").hasRole("ADMIN") // 특정 유저타입에 해당하는 회원 전체조회(ADMIN)
                .anyRequest().permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

package AcademicManagement.BCSDproject.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic().disable() // 더 이상 사용되지 않으며 제거용으로 표시되어 있는데 제거 해도 되는지
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/Student/signin").permitAll() // 로그인에 대한 권한은 다 부여
                .requestMatchers("/Student/**").hasAuthority("USER") // 아래에 대한 권한들은 1차적으로 USER에게 부여
                .requestMatchers("/Semester/**").hasAuthority("USER")
                .requestMatchers("/Score/**").hasAuthority("USER")
                .requestMatchers("/Subject/**").hasAuthority("USER")
                .requestMatchers("/GradeInformation/**").hasAuthority("USER")
                .requestMatchers("/Student/test").hasAuthority("USER")
                .anyRequest().hasRole("USER")
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
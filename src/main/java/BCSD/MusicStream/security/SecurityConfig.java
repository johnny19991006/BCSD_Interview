package BCSD.MusicStream.security;

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
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .httpBasic().disable()
                .csrf().disable()
//                .cors()
//                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/member/sign-up", "/member/sign-in", "/member/email-exists/{userEmail}").permitAll()
                .requestMatchers("/member/modify", "/member/delete").hasAnyAuthority("Admin", "User")
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/webjars/**").permitAll()
                .requestMatchers("/music/{targetText}", "/music/weather", "/music/play/{musicId}", "/music/dislike/{musicId}", "/music/like/{musicId}").hasAnyAuthority("Admin", "User")
                .requestMatchers("/music/upload", "/music/modify", "/music/delete").hasAnyAuthority("Admin")
                .requestMatchers("/playlist/**").hasAnyAuthority("Admin", "User")
                .requestMatchers("/playlist-musics/**").hasAnyAuthority("Admin", "User")
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}

package bcsd.backend.project.pokku.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtProvider jwtProvider;
    private final AuthenticationEntryPoint entryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic((httpBasicConfig) -> httpBasicConfig.disable())
                .csrf((csrfConfig) -> csrfConfig.disable())
                .cors(c -> {
                            CorsConfigurationSource source = request -> {
                                CorsConfiguration config = new CorsConfiguration();
                                config.setAllowedOrigins(List.of("*"));
                                config.setAllowedMethods(List.of("*"));
                                return config;
                            };
                            c.configurationSource(source);
                        }
                )
                .sessionManagement((sessionManagementConfigure) -> sessionManagementConfigure.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/sign/**").permitAll()
                                .requestMatchers("/user/**").hasAnyRole("User", "Admin")
                                .requestMatchers("/admin/**").hasRole("Admin")
                                .requestMatchers("/portfolio/**").hasAnyRole("User", "Admin")
                                .anyRequest().denyAll()
                )
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling((config) -> config.authenticationEntryPoint(entryPoint));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
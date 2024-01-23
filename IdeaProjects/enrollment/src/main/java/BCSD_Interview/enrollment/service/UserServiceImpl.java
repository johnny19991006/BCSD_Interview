package BCSD_Interview.enrollment.service;

import BCSD_Interview.enrollment.DTO.SignUpDTO;
import BCSD_Interview.enrollment.DTO.UserDTO;
import BCSD_Interview.enrollment.domain.JwtToken;
import BCSD_Interview.enrollment.repository.UserRepository;
import BCSD_Interview.enrollment.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (userRepository.existsByUsername(signUpDTO.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER"); // USER 권한 부여
        return UserDTO.toDTO(userRepository.save(signUpDTO.toEntity(encodedPassword, roles)));
    }

    @Transactional
    @Override
    public JwtToken signIn(String username, String password) {
        // 1. username + password를 기반으로 Authentication 객체 생성
        // 이때 authentication은 인증 여부를 확인하는 authenticated값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 User에 대한 검증 진행
        // authenticate() 메서드가 실행될 떼 CustomUserDetailsService에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtToken jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }
}

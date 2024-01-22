package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.JwtTokenDTO;
import BCSD.MusicStream.dto.SignUpDTO;
import BCSD.MusicStream.provider.JwtTokenProvider;
import BCSD.MusicStream.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
//@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    @Override
    public JwtTokenDTO signIn(String userEmail, String password) {

        // 1. username + password 를 기반으로 Authentication 객체 생성
        // 이때 authentication 은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userEmail, password);

        // 2. 실제 검증. authenticate() 메서드를 통해 요청된 Member 에 대한 검증 진행
        // authenticate 메서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtTokenDTO jwtToken = jwtTokenProvider.generateToken(authentication);

        return jwtToken;
    }

    @Override
    public void signUp(SignUpDTO signUpDTO) {
        Member users = new Member(null,
                signUpDTO.getUser_name(),
                signUpDTO.getUser_email(),
                signUpDTO.getUser_pw(),
                signUpDTO.getBirth_date(),
                signUpDTO.getAuthority_type());
        userRepository.save(users);
    }
    @Override
    public Boolean existsByUserEmail(String userEmail) {
        return userRepository.findByUserEmail(userEmail).isPresent();
    }
    @Override
    public void deleteUserByUserEmail(String userEmail) {
        try {
            userRepository.deleteUserByUserEmail(userEmail);
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("No user found with email: " + userEmail);
        }
    }
}

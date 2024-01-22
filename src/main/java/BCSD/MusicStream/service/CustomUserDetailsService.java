package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        return userRepository.findByUserEmail(userEmail).map(this::createUserDetails).orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
    }
    private UserDetails createUserDetails(Member user) {
        return User.builder()
                .username(user.getUser_email())
                .password(passwordEncoder.encode(user.getUser_pw()))
                .roles(user.getAuthority_type().toString())
                .build();
    }
}

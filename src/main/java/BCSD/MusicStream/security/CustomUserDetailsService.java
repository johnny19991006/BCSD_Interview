package BCSD.MusicStream.security;

import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.repository.MemberRepository;
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
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));
        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .id(member.getId())
                .password(passwordEncoder.encode(member.getPassword()))
                .email(member.getEmail())
                .authorityType(member.getAuthority().getType())
                .build();
        return customUserDetails;
    }
}

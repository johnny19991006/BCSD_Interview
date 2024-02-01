package BCSD.MusicStream.security;

import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
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
        Member member = memberRepository.findByEmail(memberEmail).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .id(member.getId())
                .password(member.getPassword())
                .email(member.getEmail())
                .authorityType(member.getAuthority().getType())
                .build();
        return customUserDetails;
    }
}

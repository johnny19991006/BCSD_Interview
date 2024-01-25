package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Authority;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.member.SignInMemberDTO;
import BCSD.MusicStream.dto.member.SignUpMemberDTO;
import BCSD.MusicStream.repository.AuthorityRepository;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtTokenDTO signIn(SignInMemberDTO signInMemberDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInMemberDTO.getEmail(), signInMemberDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtTokenDTO jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }
    @Override
    public void signUp(SignUpMemberDTO signUpMemberDTO) {
        Authority authority = authorityRepository.findById(signUpMemberDTO.getAuthorityId().longValue()).orElseThrow(() -> new EntityNotFoundException(""));
        memberRepository.save(Member.builder()
                .name(signUpMemberDTO.getName())
                .email(signUpMemberDTO.getEmail())
                .password(signUpMemberDTO.getPassword())
                .birthDate(signUpMemberDTO.getBirthDate())
                .authority(authority)
                .build());
    }
    @Override
    public Boolean existsByMemberEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).isPresent();
    }
    @Override
    public void deleteMemberByUserId(Integer memberId) {
        try {
            memberRepository.deleteById(memberId.longValue());
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("No user found with email: " + memberId);
        }
    }
    @Override
    public void modifyMember(ModifyMemberDTO modifyMemberDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId.longValue()).orElseThrow(() -> new EntityNotFoundException());
        Authority authority = authorityRepository.findById(modifyMemberDTO.getAuthorityId().longValue()).orElseThrow(() -> new EntityNotFoundException());
        member.setName(modifyMemberDTO.getName());
        member.setAuthority(authority);
        member.setPassword(modifyMemberDTO.getPassword());
        member.setEmail(modifyMemberDTO.getEmail());
        memberRepository.save(member);
    }
}

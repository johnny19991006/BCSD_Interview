package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Authority;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.member.ResponseMemberDTO;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.member.SignInMemberDTO;
import BCSD.MusicStream.dto.member.SignUpMemberDTO;
import BCSD.MusicStream.exception.CustomException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.AuthorityRepository;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    @Override
    public JwtTokenDTO signIn(SignInMemberDTO signInMemberDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(signInMemberDTO.getEmail(), signInMemberDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        return jwtTokenProvider.generateToken(authentication);
    }
    @Transactional
    @Override
    public ResponseMemberDTO signUp(SignUpMemberDTO signUpMemberDTO) {
        Authority authority = authorityRepository.findById(signUpMemberDTO.getAuthorityId()).orElseThrow(() -> new CustomException(ErrorCode.AUTHORITY_NOT_FOUND));
        System.out.println(passwordEncoder.encode(signUpMemberDTO.getPassword()));
        Member member = memberRepository.save(Member.builder()
                .name(signUpMemberDTO.getName())
                .email(signUpMemberDTO.getEmail())
                .password(passwordEncoder.encode(signUpMemberDTO.getPassword()))
                .birthDate(signUpMemberDTO.getBirthDate())
                .authority(authority)
                .build());
        return ResponseMemberDTO.builder()
                .id(member.getId())
                .email(member.getEmail())
                .authorityName(member.getAuthority().getType())
                .name(member.getName())
                .birthDate(member.getBirthDate())
                .build();
    }
    @Override
    public Boolean existsByMemberEmail(String memberEmail) {
        return memberRepository.findByEmail(memberEmail).isPresent();
    }
    @Transactional
    @Override
    public void deleteMemberByMemberId(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        memberRepository.deleteById(memberId);
        log.info("멤버 id:{} 삭제 완료.", memberId);

    }
    @Transactional
    @Override
    public ResponseMemberDTO modifyMember(ModifyMemberDTO modifyMemberDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Authority authority = authorityRepository.findById(modifyMemberDTO.getAuthorityId()).orElseThrow(() -> new CustomException(ErrorCode.AUTHORITY_NOT_FOUND));
        member.setName(modifyMemberDTO.getName());
        member.setAuthority(authority);
        member.setPassword(modifyMemberDTO.getPassword());
        member.setEmail(modifyMemberDTO.getEmail());
        memberRepository.save(member);
        return ResponseMemberDTO.builder()
                .id(memberId)
                .name(modifyMemberDTO.getName())
                .email(modifyMemberDTO.getEmail())
                .authorityName(authority.getType())
                .birthDate(modifyMemberDTO.getBirthDate())
                .build();
    }
}

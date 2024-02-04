package BCSD.MusicStream.service;

import BCSD.MusicStream.domain.Authority;
import BCSD.MusicStream.domain.Member;
import BCSD.MusicStream.dto.member.*;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.exception.CustomErrorCodeException;
import BCSD.MusicStream.exception.ErrorCode;
import BCSD.MusicStream.repository.AuthorityRepository;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

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
        Authority authority = authorityRepository.findById(signUpMemberDTO.getAuthorityId()).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.AUTHORITY_NOT_FOUND));
        if(signUpMemberDTO.getBirthDate().isAfter(LocalDate.now())) throw new CustomErrorCodeException(ErrorCode.INVALID_BIRTH_DATE);
        Optional<Member> isSameEmail = memberRepository.findByEmail(signUpMemberDTO.getEmail());
        if(isSameEmail.isPresent()) throw new CustomErrorCodeException(ErrorCode.INVALID_MEMBER_EMAIL);
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
                .authorityId(member.getAuthority().getId())
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
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        memberRepository.deleteById(memberId);
        log.info("멤버 id:{} 삭제 완료.", memberId);
    }
    @Transactional
    @Override
    public ResponseMemberDTO modifyMember(ModifyMemberInfoDTO modifyMemberInfoDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        if(modifyMemberInfoDTO.getBirthDate().isAfter(LocalDate.now())) throw new CustomErrorCodeException(ErrorCode.INVALID_BIRTH_DATE);
        member.setName(modifyMemberInfoDTO.getName());
        member.setEmail(modifyMemberInfoDTO.getEmail());
        member.setBirthDate(modifyMemberInfoDTO.getBirthDate());
        return ResponseMemberDTO.builder()
                .id(memberId)
                .name(modifyMemberInfoDTO.getName())
                .email(modifyMemberInfoDTO.getEmail())
                .authorityId(member.getAuthority().getId())
                .birthDate(modifyMemberInfoDTO.getBirthDate())
                .build();
    }

    @Override
    public ResponseMemberDTO getMemberInfo(Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        return ResponseMemberDTO.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .authorityId(member.getAuthority().getId())
                .birthDate(member.getBirthDate())
                .build();
    }

    @Transactional
    @Override
    public Boolean modifyPassword(ModifyMemberPasswordDTO modifyMemberPasswordDTO, Integer memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomErrorCodeException(ErrorCode.MEMBER_NOT_FOUND));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), modifyMemberPasswordDTO.getOrigin());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        member.setPassword(passwordEncoder.encode(modifyMemberPasswordDTO.getChange()));
        return true;
    }
}

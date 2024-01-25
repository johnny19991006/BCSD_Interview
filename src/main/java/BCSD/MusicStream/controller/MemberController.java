package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.dto.member.SignInMemberDTO;
import BCSD.MusicStream.dto.member.SignUpMemberDTO;
import BCSD.MusicStream.security.JwtTokenProvider;
import BCSD.MusicStream.service.MemberService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/sign-in")
    public JwtTokenDTO signIn(@RequestBody SignInMemberDTO signInMemberDTO) {
        try {
            JwtTokenDTO jwtTokenDTO = memberService.signIn(signInMemberDTO);
            log.info("User signed in with email: {}", signInMemberDTO.getEmail());
            return jwtTokenDTO;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/email-exists/{userEmail}")
    public Boolean existsUserEmail(@PathVariable String userEmail) {
        return memberService.existsByMemberEmail(userEmail);
    }

    @PostMapping
    public ResponseEntity<SignUpMemberDTO> signUp(@RequestBody SignUpMemberDTO signUpMemberDTO) {
        memberService.signUp(signUpMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpMemberDTO);
    }

    @PutMapping
    public ResponseEntity<?> modifyMember(HttpServletRequest request, @RequestBody ModifyMemberDTO modifyMemberDTO) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        memberService.modifyMember(modifyMemberDTO, memberId);
        return ResponseEntity.ok("수정완료");
    }
    @DeleteMapping
    public ResponseEntity<?> deleteMember(HttpServletRequest request) {
        Claims cLaims = JwtTokenProvider.parseClaims(JwtTokenProvider.extractJwtFromRequest(request));
        Integer memberId = (Integer) cLaims.get("memberId");
        memberService.deleteMemberByUserId(memberId);
        return ResponseEntity.ok("Ok");
    }
}

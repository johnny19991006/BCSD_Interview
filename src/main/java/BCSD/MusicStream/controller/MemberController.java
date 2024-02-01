package BCSD.MusicStream.controller;

import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.member.ModifyMemberDTO;
import BCSD.MusicStream.dto.member.ResponseMemberDTO;
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
    public ResponseEntity<JwtTokenDTO> signIn(@RequestBody SignInMemberDTO signInMemberDTO) {
        try {
            JwtTokenDTO jwtTokenDTO = memberService.signIn(signInMemberDTO);
            log.info("User signed in with email: {}", signInMemberDTO.getEmail());
            return ResponseEntity.ok(jwtTokenDTO);
        } catch (Exception e) {
            log.error("Sign-in error: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/email-exists/{userEmail}")
    public ResponseEntity<Boolean> existsUserEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(memberService.existsByMemberEmail(userEmail));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMemberDTO> signUp(@RequestBody SignUpMemberDTO signUpMemberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(signUpMemberDTO));
    }

    @PutMapping
    public ResponseEntity<ResponseMemberDTO> modifyMember(HttpServletRequest request, @RequestBody ModifyMemberDTO modifyMemberDTO) {
        return ResponseEntity.ok(memberService.modifyMember(modifyMemberDTO, WebConfig.getMemberIdByRequest(request)));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteMember(HttpServletRequest request) {
        memberService.deleteMemberByMemberId(WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }
}

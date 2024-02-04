package BCSD.MusicStream.controller;

import BCSD.MusicStream.config.WebConfig;
import BCSD.MusicStream.dto.member.*;
import BCSD.MusicStream.dto.token.JwtTokenDTO;
import BCSD.MusicStream.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    public ResponseEntity<JwtTokenDTO> signIn(@Valid @RequestBody SignInMemberDTO signInMemberDTO) {
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

    @GetMapping("/info")
    public ResponseEntity<ResponseMemberDTO> getMemberInfo(HttpServletRequest request) {
        return ResponseEntity.ok(memberService.getMemberInfo(WebConfig.getMemberIdByRequest(request)));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<ResponseMemberDTO> signUp(@Valid @RequestBody SignUpMemberDTO signUpMemberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.signUp(signUpMemberDTO));
    }

    @PutMapping("/modify-info")
    public ResponseEntity<ResponseMemberDTO> modifyMember(HttpServletRequest request, @Valid @RequestBody ModifyMemberInfoDTO modifyMemberDTO) {
        return ResponseEntity.ok(memberService.modifyMember(modifyMemberDTO, WebConfig.getMemberIdByRequest(request)));
    }

    @PutMapping("/modify-password")
    public ResponseEntity<Boolean> modifyMember(HttpServletRequest request, @Valid @RequestBody ModifyMemberPasswordDTO modifyMemberPasswordDTO) {
        return ResponseEntity.ok(memberService.modifyPassword(modifyMemberPasswordDTO, WebConfig.getMemberIdByRequest(request)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMember(HttpServletRequest request) {
        memberService.deleteMemberByMemberId(WebConfig.getMemberIdByRequest(request));
        return ResponseEntity.noContent().build();
    }
}

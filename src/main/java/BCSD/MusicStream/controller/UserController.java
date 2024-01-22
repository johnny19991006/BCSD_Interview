package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.JwtTokenDTO;
import BCSD.MusicStream.dto.SignInDTO;
import BCSD.MusicStream.dto.SignUpDTO;
import BCSD.MusicStream.dto.UserDTO;
import BCSD.MusicStream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/sign-in")
    public JwtTokenDTO signIn(@RequestBody SignInDTO signInDTO) {
        JwtTokenDTO jwtTokenDTO = userService.signIn(signInDTO.getUserEmail(), signInDTO.getPassword());
        log.info("User signed in with email: {}", signInDTO.getUserEmail());
        return jwtTokenDTO;
    }
    @GetMapping("/email-exists/{userEmail}")
    public Boolean existsUserEmail(@PathVariable String userEmail) {
        return userService.existsByUserEmail(userEmail);
    }

    @PostMapping
    public ResponseEntity<SignUpDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        userService.signUp(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpDTO);
    }
    @PostMapping("/test")
    public String test() {
        return "success";
    }
}

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
        String userEmail = signInDTO.getUserEmail();
        String password = signInDTO.getPassword();
        JwtTokenDTO jwtTokenDTO = userService.signIn(userEmail, password);
        log.info("request username = {}, password = {}", userEmail, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtTokenDTO.getAccessToken(), jwtTokenDTO.getRefreshToken());
        return jwtTokenDTO;
    }
    @PostMapping("/sign-up/{userEmail}")
    public Boolean existsUserEmail(@PathVariable String userEmail) {
        return userService.existsByUserEmail(userEmail);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<SignUpDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        System.out.println("dsa");
        userService.signUp(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpDTO);
    }
    @PostMapping("/test")
    public String test() {
        System.out.println("dsa");
        return "success";
    }
}

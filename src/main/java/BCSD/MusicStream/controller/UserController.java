package BCSD.MusicStream.controller;

import BCSD.MusicStream.dto.JwtTokenDTO;
import BCSD.MusicStream.dto.SignInDTO;
import BCSD.MusicStream.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    @PostMapping("/test")
    public String test() {
        System.out.println("dsa");
        return "success";
    }
}

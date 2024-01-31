package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.service.TokenService;
import HSAnimal.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;

    public LoginController(UserService userService, TokenService tokenService){
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.authenticateUser(userDTO);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "로그아웃 되었습니다.";
    }
}

package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(UserDTO dto) {
        userService.save(dto);
        return "redirect:/login";
    }

    @PostMapping("/login/proc")
    public String processLogin(@RequestParam("userId") String userId, @RequestParam("password") String password) {
        if (userService.authenticateUser(userId, password)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return "redirect:/home";
        }
        else {
            return "redirect:/login?error"; // 실패 응답
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}

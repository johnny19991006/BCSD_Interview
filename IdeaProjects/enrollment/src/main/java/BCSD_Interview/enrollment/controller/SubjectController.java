package BCSD_Interview.enrollment.controller;

import BCSD_Interview.enrollment.DTO.SignInDTO;
import BCSD_Interview.enrollment.DTO.SignUpDTO;
import BCSD_Interview.enrollment.DTO.UserDTO;
import BCSD_Interview.enrollment.domain.JwtToken;
import BCSD_Interview.enrollment.domain.User;
import BCSD_Interview.enrollment.security.SecurityUtil;
import BCSD_Interview.enrollment.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO savedUserDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDTO signInDTO) {
        String username = signInDTO.getUsername();
        String password = signInDTO.getPassword();
        JwtToken jwtToken = userService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("jwtToken accessToken = {}, refreshToken = {}", jwtToken.getAccessToken(), jwtToken.getRefreshToken());
        return jwtToken;
    }

    @PostMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUsername();
    }

    @PostMapping("/")
    public User insertUser(@RequestBody User user) { return userService.insertUser(user); }

    @GetMapping("")
    public List<User> getAllUsers() { return userService.getAllUsers(); }

    @GetMapping("/{userId}")
    public User getUserByUserId(@PathVariable Long userId) { return userService.getUserByUserId(userId); }

    @PutMapping("/{userId}/name")
    public void  updateUsername(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateUsername(userId, userDTO.getUsername());
    }

    @PutMapping("/{userId}/pw")
    public void  updatePassword(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updatePassword(userId, userDTO.getPassword());
    }

    @PutMapping("/{userId}/dept")
    public void  updateDepartment(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateDepartment(userId, userDTO.getDepartment());
    }

    @PutMapping("/{userId}/grade")
    public void  updateGrade(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateGrade(userId, userDTO.getGrade());
    }

    @PutMapping("/{userId}/rg")
    public void  updateRegistrable_Grade(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        userService.updateRegistrable_Grade(userId, userDTO.getRegistrable_grade());
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) { userService.deleteUser(userId); }
}
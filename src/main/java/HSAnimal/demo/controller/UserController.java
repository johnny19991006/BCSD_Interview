package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import HSAnimal.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService){
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("\"" + userService.signup(request) + "\"로 회원가입이 완료되었습니다!");
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.authenticateUser(userDTO);
    }

    @GetMapping("/logout")
    public String logout() {
        // 리프레시 토큰, 액세스 토큰 => 블랙리스트 추가
        return "로그아웃 되었습니다.";
    }

    // 정보 조회
    @GetMapping("/{user_id}")
    public User read(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    // 정보 수정
    @PutMapping("/{user_id}")
    public User update(@PathVariable String user_id, @RequestBody User updatedUser) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> delete(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }
}

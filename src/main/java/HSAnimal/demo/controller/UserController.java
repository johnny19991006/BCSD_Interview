package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import HSAnimal.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
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
                    // 업데이트할 필드 설정
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    // 필요한 다른 필드 업데이트

                    // 저장하고 업데이트된 사용자 반환
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> delete(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}

package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.CreateAccessTokenResponseDTO;
import HSAnimal.demo.DTO.UpdateUserDTO;
import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import HSAnimal.demo.service.TokenService;
import HSAnimal.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenService tokenService;

    public UserController(UserRepository userRepository, UserService userService, TokenService tokenService){
        this.userRepository = userRepository;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("\"" + userService.signup(request) + "\"로 회원가입이 완료되었습니다!");
    }

    @PostMapping("/login")
    public ResponseEntity<CreateAccessTokenResponseDTO> login(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.authenticateUser(userDTO));
    }

    @PostMapping("/{user_id}/logout")
    public ResponseEntity<String> logout(@PathVariable String user_id) {
        tokenService.deleteRefreshToken(user_id);
        return ResponseEntity.status(HttpStatus.CREATED).body("로그아웃 되었습니다.");
    }

    @GetMapping("/{user_id}")
    public User readUser(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<String> updateUser(@PathVariable String user_id, @RequestBody UpdateUserDTO updatedUserDTO) {
        userService.updateUser(user_id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원정보가 수정되었습니다.");
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok("회원탈퇴가 완료되었습니다.");
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }
}

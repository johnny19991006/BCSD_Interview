package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.entity.User;
import HSAnimal.demo.repository.UserRepository;
import HSAnimal.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private final UserService userService;

    // Create: 사용자 추가
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDTO dto) {
        Long userId = userService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User with ID " + userId + " registered successfully.");
    }

    // Read: 사용자 검색
    @GetMapping("/{user_id}")
    public User read(@PathVariable String user_id) {
        return userRepository.findByUserId(user_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

    // Update: 사용자 정보 수정
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

    // Delete: 사용자 삭제
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
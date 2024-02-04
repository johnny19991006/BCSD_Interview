package HSAnimal.demo.controller;

import HSAnimal.demo.DTO.CreateAccessTokenDto;
import HSAnimal.demo.DTO.UpdateUserDto;
import HSAnimal.demo.DTO.UserDto;
import HSAnimal.demo.DTO.UserKeywordsDto;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import HSAnimal.demo.service.MatchService;
import HSAnimal.demo.service.TokenService;
import HSAnimal.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final TokenService tokenService;
    private final MatchService matchService;

    public UserController(UserRepository userRepository, UserService userService,
                          TokenService tokenService, MatchService matchService){
        this.userRepository = userRepository;
        this.userService = userService;
        this.tokenService = tokenService;
        this.matchService = matchService;
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("\"" + userService.signup(request) + "\" 회원가입이 완료되었습니다!");
    }

    @PostMapping("/login")
    public ResponseEntity<CreateAccessTokenDto> login(@RequestBody UserDto userDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(userDTO));
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
    public ResponseEntity<String> updateUser(@PathVariable String user_id, @RequestBody UpdateUserDto updatedUserDTO) {
        userService.updateUser(user_id, updatedUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원정보가 수정되었습니다.");
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable String user_id) {
        userService.deleteUser(user_id);
        return ResponseEntity.status(HttpStatus.OK).body("회원탈퇴가 완료되었습니다.");
    }

    @GetMapping("/{user_id}/keywords")
    public ResponseEntity<Set<Integer>> showUserKeywords(@PathVariable String user_id) {
        return ResponseEntity.status(HttpStatus.OK).body(matchService.getMyOptionList(user_id));
    }

    @DeleteMapping("/{user_id}/keywords")
    public ResponseEntity<Set<Integer>> deleteUserKeywords(@PathVariable String user_id,
                                                           @RequestBody List<UserKeywordsDto> userKeywordsList) {
        userService.deleteUserKeywords(user_id, userKeywordsList);
        return ResponseEntity.status(HttpStatus.OK).body(matchService.getMyOptionList(user_id));
    }

}

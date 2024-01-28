package com.RBook.board.controller;

import com.RBook.board.config.auth.JwtTokenUtil;
import com.RBook.board.domain.User;
import com.RBook.board.dto.LoginRequest;
import com.RBook.board.dto.UserDTO;
import com.RBook.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("/join")
    public String saveUser() {
        return "users/saveUser";
    }
    @PostMapping("/join")
    public String saveUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "home";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        User user = userService.login(req);
        if (user == null) {
            return "아이디 또는 비밀번호가 일치하지 않습니다.";
        }
        // 로그인 성공
        String secretKey = "my-secret-key-123123";
        long expireTimeMs = 1000 * 60 * 60; //60분

        String jwtToken = JwtTokenUtil.createToken(user.getLoginId(), secretKey, expireTimeMs);

        return jwtToken;
    }

    @GetMapping("/{id}")
    public String getUserPwById(@PathVariable Long id) {return userService.getUserPwById(id);}

    @GetMapping("/myUser/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/myUser/edit/")
    public UserDTO modifyUser(@RequestBody UserDTO userDTO) {return userService.saveUser(userDTO);}

    @DeleteMapping("/myUser/delete/{id}")
    public void deleteUser(@PathVariable Long id) {userService.deleteUser(id);}
}

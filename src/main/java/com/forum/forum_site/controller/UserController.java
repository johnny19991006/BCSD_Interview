package com.forum.forum_site.controller;

import com.forum.forum_site.domain.Post;
import com.forum.forum_site.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.forum.forum_site.domain.User;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User findUsers(@PathVariable(name = "id") Integer id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @PutMapping("/{id}")
    public void updateUserName(@RequestParam @PathVariable(name = "id") Integer id, @RequestBody User user) {
        userService.updateUsername(id, user);
    }

    // 회원가입
    @PostMapping("/join")
    public void join(@RequestBody Map<String, String> user) {
        userService.joinUser(user);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        return userService.loginUser(user);
    }

}
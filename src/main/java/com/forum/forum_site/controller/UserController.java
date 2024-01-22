package com.forum.forum_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.forum.forum_site.service.UserServiceImpl;
import com.forum.forum_site.domain.User;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("")
    public List<User> findAll() {
        return userServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public User findUsers(@PathVariable(name = "id") Integer id) {
        return userServiceImpl.findById(id);
    }

    @PostMapping("")
    public User createUser(@RequestBody User newUser) {
        return userServiceImpl.createUser(newUser);
    }

    @PutMapping("/{id}")
    public void updateUserName(@RequestParam @PathVariable(name = "id") Integer id, @RequestBody User user) {
        userServiceImpl.updateUsername(id, user);
    }

    // 회원가입
    @PostMapping("/join")
    public void join(@RequestBody Map<String, String> user) {
        userServiceImpl.joinUser(user);
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        return userServiceImpl.loginUser(user);
    }
}
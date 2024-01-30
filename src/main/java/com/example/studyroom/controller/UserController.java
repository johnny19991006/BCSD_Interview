package com.example.studyroom.controller;

import com.example.studyroom.domain.User;
import com.example.studyroom.dto.LoginDTO;
import com.example.studyroom.dto.UserDTO;
import com.example.studyroom.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{schoolId}")
    public User getUserByUserId(@PathVariable int schoolId) {
        return userService.getUserByUserId(schoolId);
    }

    @DeleteMapping("/{schoolId}")
    public void deleteUser(@PathVariable int schoolId) {
        userService.deleteUser(schoolId);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO){
        return userService.loginUser(loginDTO);
    }
}

package com.example.studyroom.controller;

import com.example.studyroom.domain.User;
import com.example.studyroom.dto.UserDTO;
import com.example.studyroom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("")
    public User insertUser(@RequestBody UserDTO user) {
        return userService.insertUser(user);
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
}

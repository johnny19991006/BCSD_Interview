package com.example.board.controller;

import com.example.board.domain.User;
import com.example.board.dto.LoginRequestDTO;
import com.example.board.dto.UserDTO;
import com.example.board.security.AuthorizeUser;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User insertUser(@RequestBody User user) throws SQLException {
        return userService.insertUser(user);
    }
    @GetMapping
    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }
    @GetMapping("/{userId}/admin")
    public User getUserByUserId(@PathVariable Integer userId) throws SQLException {
        return userService.getUserByUserId(userId);
    }
    @GetMapping("/{userId}/general")
    public UserDTO getUserSimpleInfoByUserId(@PathVariable Integer userId) throws SQLException {
        return userService.getUserSimpleInfoByUserId(userId);
    }
    @AuthorizeUser
    @PutMapping("/{userId}/password") //DTO 사용하는 방식도 있음
    public void updateUserPw(@PathVariable Integer userId, @RequestBody String newPw) throws SQLException {
        userService.updateUserPw(userId, newPw);
    }
    @AuthorizeUser
    @PutMapping("/{userId}/nickname")
    public void updateUserNn(@PathVariable Integer userId, @RequestBody String newNn) throws SQLException {
        userService.updateUserNn(userId, newNn);
    }
    @PutMapping("/{userId}/type")
    public void updateUsertype(@PathVariable Integer userId, @RequestBody Integer newTypeNum) throws SQLException {
        userService.updateUsertype(userId, newTypeNum);
    }
    @AuthorizeUser
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) throws SQLException {
        userService.deleteUser(userId);
    }
    @GetMapping("/usertype/{userTypeId}")
    public List<User> getUsersByUserType(@PathVariable int userTypeId) throws SQLException {
        return userService.getUsersByUserType(userTypeId);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) throws SQLException {
        return userService.login(loginRequestDTO);
    }
}

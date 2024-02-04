package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponseDTO> insertUser(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(userRequestDTO));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @GetMapping("/{userId}/admin")
    public ResponseEntity<UserResponseDTO> getUserByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getUserByUserId(userId));
    }
    @GetMapping("/{userId}/general")
    public ResponseEntity<UserResponseSimpleDTO> getUserSimpleInfoByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok().body(userService.getUserSimpleInfoByUserId(userId));
    }
    @GetMapping("/usertype/{userTypeId}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByUserType(@PathVariable int userTypeId) {
        return ResponseEntity.ok().body(userService.getUsersByUserType(userTypeId));
    }
    @PutMapping("/{userId}/type")
    public ResponseEntity<UserResponseDTO> updateUsertype(@PathVariable Integer userId, @RequestBody Integer newTypeNum) {
        return ResponseEntity.ok().body(userService.updateUsertype(userId, newTypeNum));
    }
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updateUserPw(@PathVariable Integer userId, @RequestBody String newPw) throws SQLException {
        userService.updateUserPw(userId, newPw);
        return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다");
    }
    @PutMapping("/{userId}/nickname")
    public ResponseEntity<UserResponseDTO> updateUserNn(@PathVariable Integer userId, @RequestBody String newNn) {
        return ResponseEntity.ok().body(userService.updateUserNn(userId, newNn));
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok().body(userService.login(loginRequestDTO));
    }
}

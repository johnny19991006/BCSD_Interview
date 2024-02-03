package com.example.board.controller;

import com.example.board.domain.User;
import com.example.board.dto.LoginRequestDTO;
import com.example.board.dto.UserRequestDTO;
import com.example.board.dto.UserResponseDTO;
import com.example.board.dto.UserResponseSimpleDTO;
import com.example.board.exception.NotFoundException;
import com.example.board.security.AuthorizeUser;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.insertUser(userRequestDTO));
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
    @GetMapping("/{userId}/admin")
    public ResponseEntity<UserResponseDTO> getUserByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok().body(userService.getUserByUserId(userId));
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/{userId}/general")
    public ResponseEntity<UserResponseSimpleDTO> getUserSimpleInfoByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok().body(userService.getUserSimpleInfoByUserId(userId));
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping("/usertype/{userTypeId}")
    public ResponseEntity<List<UserResponseDTO>> getUsersByUserType(@PathVariable int userTypeId) {
        try {
            return ResponseEntity.ok().body(userService.getUsersByUserType(userTypeId));
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PutMapping("/{userId}/type")
    public ResponseEntity<UserResponseDTO> updateUsertype(@PathVariable Integer userId, @RequestBody Integer newTypeNum) {
        try {
            return ResponseEntity.ok().body(userService.updateUsertype(userId, newTypeNum));
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @AuthorizeUser
    @PutMapping("/{userId}/password")
    public void updateUserPw(@PathVariable Integer userId, @RequestBody String newPw) throws SQLException {
        userService.updateUserPw(userId, newPw);
    }
    @AuthorizeUser
    @PutMapping("/{userId}/nickname")
    public void updateUserNn(@PathVariable Integer userId, @RequestBody String newNn) throws SQLException {
        userService.updateUserNn(userId, newNn);
    }
    @AuthorizeUser
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Integer userId) throws SQLException {
        userService.deleteUser(userId);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) throws SQLException {
        return userService.login(loginRequestDTO);
    }
}

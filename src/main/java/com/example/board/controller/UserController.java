package com.example.board.controller;

import com.example.board.dto.*;
import com.example.board.exception.NotFoundException;
import com.example.board.exception.UnauthorizedException;
import com.example.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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
    @PutMapping("/{userId}/password")
    public ResponseEntity<String> updateUserPw(@PathVariable Integer userId, @RequestBody String newPw) throws SQLException {
        try {
            userService.updateUserPw(userId, newPw);
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다");
        }
        catch (UnauthorizedException | ClassCastException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("권한이 없습니다");
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다");
        }
    }
    @PutMapping("/{userId}/nickname")
    public ResponseEntity<UserResponseDTO> updateUserNn(@PathVariable Integer userId, @RequestBody String newNn) {
        try {
            return ResponseEntity.ok().body(userService.updateUserNn(userId, newNn));
        }
        catch (UnauthorizedException | ClassCastException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.noContent().build();
        }
        catch (UnauthorizedException | ClassCastException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            return ResponseEntity.ok().body(userService.login(loginRequestDTO));
        }
        catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

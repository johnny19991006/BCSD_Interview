package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.dto.*;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.List;

public interface UserService {
    public UserResponseDTO insertUser(UserRequestDTO userRequestDTO);
    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserByUserId(Integer userId);
    public UserResponseSimpleDTO getUserSimpleInfoByUserId(Integer userId);
    public void updateUserPw(Integer userId, String newPw);
    public UserResponseDTO updateUserNn(Integer userId, String newNn);
    public UserResponseDTO updateUsertype(Integer userId, Integer newTypeNum);
    public void deleteUser(Integer userId);
    public List<UserResponseDTO> getUsersByUserType(int userTypeId);
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws BadCredentialsException;
    public User getUserById(Integer userId);
}

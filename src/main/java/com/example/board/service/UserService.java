package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.dto.LoginRequestDTO;
import com.example.board.dto.UserRequestDTO;
import com.example.board.dto.UserResponseDTO;
import com.example.board.dto.UserResponseSimpleDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public UserResponseDTO insertUser(UserRequestDTO userRequestDTO);
    public List<UserResponseDTO> getAllUsers();
    public UserResponseDTO getUserByUserId(Integer userId);
    public UserResponseSimpleDTO getUserSimpleInfoByUserId(Integer userId);
    public void updateUserPw(Integer userId, String newPw) throws SQLException;
    public void updateUserNn(Integer userId, String newNn) throws SQLException;
    public UserResponseDTO updateUsertype(Integer userId, Integer newTypeNum);
    public void deleteUser(Integer userId) throws SQLException;
    public List<UserResponseDTO> getUsersByUserType(int userTypeId);
    public String login(LoginRequestDTO loginRequestDTO) throws SQLException;
    public User getUserById(Integer userId);
}

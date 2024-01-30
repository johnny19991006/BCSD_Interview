package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.dto.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User insertUser(User user) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public User getUserByUserId(Integer userId) throws SQLException;
    public UserDTO getUserSimpleInfoByUserId(Integer userId) throws SQLException;
    public void updateUserPw(Integer userId, String newPw) throws SQLException;
    public void updateUserNn(Integer userId, String newNn) throws SQLException;
    public void updateUsertype(Integer userId, Integer newTypeNum) throws SQLException;
    public void deleteUser(Integer userId) throws SQLException;
    public List<User> getUsersByUserType(int userTypeId) throws SQLException;
}

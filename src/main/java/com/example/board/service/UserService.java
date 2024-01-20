package com.example.board.service;

import com.example.board.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User insertUser(User user) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public User getUserByUserId(Long userId) throws SQLException;
    public void updateUserPw(Long userId, User user) throws SQLException;
    public void deleteUser(Long userId) throws SQLException;
}

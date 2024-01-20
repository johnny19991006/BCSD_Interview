package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public User insertUser(User user) throws SQLException { // 등록
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() throws SQLException { // 전체조회
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(Long userId) throws SQLException { // 특정조회
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserPw(Long userId, User user) throws SQLException { // 수정
        User userInf = userRepository.findById(userId).orElse(null);
        if(userInf != null) {
            userInf.setUserpw(user.getUserpw());
            userRepository.save(userInf);
        }
    }

    @Override
    public void deleteUser(Long userId) throws SQLException { // 삭제
        userRepository.deleteById(userId);
    }
}

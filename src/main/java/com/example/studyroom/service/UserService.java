package com.example.studyroom.service;

import com.example.studyroom.domain.User;
import com.example.studyroom.dto.UserDTO;
import com.example.studyroom.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User insertUser(UserDTO user) {
        return userRepository.save(User.builder()
                .schoolId(user.getSchoolId())
                .name(user.getName())
                .password(user.getPassword())
                .build());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserId(Integer schoolId) {
        return userRepository.findById(schoolId).orElse(null);
    }

    public void deleteUser(Integer schoolId) {
        userRepository.deleteById(schoolId);
    }

}

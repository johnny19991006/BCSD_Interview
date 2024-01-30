package com.example.studyroom.service;

import com.example.studyroom.auth.JWTProvider;
import com.example.studyroom.domain.User;
import com.example.studyroom.dto.LoginDTO;
import com.example.studyroom.dto.UserDTO;
import com.example.studyroom.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTProvider jwtProvider;

    public UserService(UserRepository userRepository, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    public User registerUser(UserDTO user) {
        String password = hashPassword(user.getPassword());

        return userRepository.save(User.builder()
                .schoolId(user.getSchoolId())
                .name(user.getName())
                .password(password)
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

    public String loginUser(LoginDTO loginDTO) {
        User user = userRepository.findBySchoolId(loginDTO.getSchoolId());

        if (!checkPass(loginDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다!");
        }

        return jwtProvider.createToken(user.getSchoolId());
    }

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    private boolean checkPass(String plainPassword, String hashedPassword) {

        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}

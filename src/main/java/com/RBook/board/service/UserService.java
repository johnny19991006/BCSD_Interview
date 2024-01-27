package com.RBook.board.service;


import com.RBook.board.domain.User;
import com.RBook.board.dto.LoginRequest;
import com.RBook.board.dto.UserDTO;
import com.RBook.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserDTO saveUser(UserDTO userDTO) {
        userRepository.save(userDTO.toEntity());
        return userDTO;
    }
    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByLoginId(req.getLoginId());

        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        if(!encoder.matches(req.getLoginPw(), user.getLoginPw())) {
            return null;
        }
        return user;
    }

    public UserDTO getUserById(Long id) {
        if(id == null) return null;
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.get();

        UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .loginID(user.getLoginId())
                .loginPw(user.getLoginPw())
                .userName(user.getUserName())
                .nickname(user.getNickname())
                .userEmail(user.getUserEmail())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .createDate(user.getCreateDate())
                .modifyDate(user.getModifyDate())
                .build();
        return userDTO;
    }

    public String getUserPwById(Long id) {
        if(id == null) return null;
        Optional<User> optionalUser = userRepository.findById(id);
        String userPw = optionalUser.get().getLoginPw();

        return userPw;
    }

    public User getLoginUserByLoginId(String loginId) {
        if (loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        return optionalUser.orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}

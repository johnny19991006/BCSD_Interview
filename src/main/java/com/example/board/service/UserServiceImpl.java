package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.domain.Usertype;
import com.example.board.dto.LoginRequestDTO;
import com.example.board.dto.UserRequestDTO;
import com.example.board.dto.UserResponseDTO;
import com.example.board.dto.UserResponseSimpleDTO;
import com.example.board.exception.NotFoundException;
import com.example.board.repository.UserRepository;
import com.example.board.repository.UsertypeRepository;
import com.example.board.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UsertypeRepository usertypeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userrepository, UsertypeRepository usertypeRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userrepository;
        this.usertypeRepository = usertypeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @Override
    public UserResponseDTO insertUser(UserRequestDTO userRequestDTO) { // 유저등록
        String rawPassword = userRequestDTO.getUserPassword();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);

        Usertype usertype = usertypeRepository.findById(userRequestDTO.getUserTypeId())
                .orElseThrow(() -> new NotFoundException(""));

        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .userEmail(userRequestDTO.getUserEmail())
                .userPw(encodedPassword)
                .userNickname(userRequestDTO.getUserNickname())
                .userType(usertype)
                .build();

        User savedUser = userRepository.save(user);

        return UserResponseDTO.builder()
                .userId(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .userEmail(savedUser.getUserEmail())
                .userNickname(savedUser.getUserNickname())
                .createdAt(savedUser.getCreatedAt())
                .userType(savedUser.getUserType())
                .build();
    }
    @Override
    public List<UserResponseDTO> getAllUsers() { // 전체조회 (오름차순)
        List<User> users = userRepository.findAllByOrderByUserIdAsc();
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .userNickname(user.getUserNickname())
                    .createdAt(user.getCreatedAt())
                    .userType(user.getUserType())
                    .build();

            userResponseDTOs.add(userResponseDTO);
        }

        return userResponseDTOs;
    }
    @Override
    public UserResponseDTO getUserByUserId(Integer userId) { // 특정조회 (userid기준, admin용)
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(""));
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userEmail(user.getUserEmail())
                .userNickname(user.getUserNickname())
                .createdAt(user.getCreatedAt())
                .userType(user.getUserType())
                .build();
    }
    @Override
    public UserResponseSimpleDTO getUserSimpleInfoByUserId(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(""));
        return UserResponseSimpleDTO.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .userNickname(user.getUserNickname())
                .createdAt(user.getCreatedAt())
                .userType(user.getUserType())
                .build();
    }
    @Override
    public UserResponseDTO updateUsertype(Integer userId, Integer newTypeNum) {
        User userInf = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(""));
        Usertype newUsertype = usertypeRepository.findById(newTypeNum).orElseThrow(()-> new NotFoundException(""));

        User updatedUser = User.builder()
                .userId(userInf.getUserId())
                .userName(userInf.getUserName())
                .userEmail(userInf.getUserEmail())
                .userPw(userInf.getUserPw())
                .userNickname(userInf.getUserNickname())
                .createdAt(userInf.getCreatedAt())
                .userType(newUsertype)
                .build();

        userRepository.save(updatedUser);

        return UserResponseDTO.builder()
                .userId(updatedUser.getUserId())
                .userName(updatedUser.getUserName())
                .userEmail(updatedUser.getUserEmail())
                .userNickname(updatedUser.getUserNickname())
                .createdAt(updatedUser.getCreatedAt())
                .userType(updatedUser.getUserType())
                .build();
    }
    @Override
    public void updateUserPw(Integer userId, String newPw) throws SQLException { // 비밀번호수정
        User userInf = userRepository.findById(userId).orElse(null);
        if(userInf != null) {
            userInf.setUserPw(newPw);
            userRepository.save(userInf);
        }
    }
    @Override
    public void updateUserNn(Integer userId, String newNn) throws SQLException { // 닉네임수정
        User userInf = userRepository.findById(userId).orElse(null);
        if(userInf != null) {
            userInf.setUserNickname(newNn);
            userRepository.save(userInf);
        }
    }
    @Override
    public void deleteUser(Integer userId) throws SQLException { // 회원삭제
        userRepository.deleteById(userId);
    }
    @Override
    public List<UserResponseDTO> getUsersByUserType(int userTypeId) {
        usertypeRepository.findById(userTypeId).orElseThrow(() -> new NotFoundException(""));

        List<User> users = userRepository.findByUserTypeUserTypeId(userTypeId);
        List<UserResponseDTO> userResponseDTOs = new ArrayList<>();

        for (User user : users) {
            UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userEmail(user.getUserEmail())
                    .userNickname(user.getUserNickname())
                    .createdAt(user.getCreatedAt())
                    .userType(user.getUserType())
                    .build();

            userResponseDTOs.add(userResponseDTO);
        }

        return userResponseDTOs;
    }
    @Override
    public String login(LoginRequestDTO loginRequestDTO) throws SQLException {
        User user = userRepository.findByUserEmail(loginRequestDTO.getEmail()).orElseThrow(()
                -> new BadCredentialsException("잘못된 계정정보입니다."));
        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getUserPw())) {
            throw new BadCredentialsException("잘못된 계정정보입니다.");
        }

        String jwtToken = jwtTokenProvider.createToken(user.getUserEmail(), user.getUserType());
        return "로그인 성공 " + jwtToken;
    }
    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
}

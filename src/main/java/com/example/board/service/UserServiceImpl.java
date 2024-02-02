package com.example.board.service;

import com.example.board.domain.User;
import com.example.board.domain.Usertype;
import com.example.board.dto.LoginRequestDTO;
import com.example.board.dto.UserDTO;
import com.example.board.repository.UserRepository;
import com.example.board.repository.UsertypeRepository;
import com.example.board.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    public User insertUser(User user) throws SQLException { // 유저등록
        String rawPassword = user.getUserPw();
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setUserPw(encodedPassword);
        return userRepository.save(user);
    }
    @Override
    public List<User> getAllUsers() throws SQLException { // 전체조회 (오름차순)
        return userRepository.findAllByOrderByUserIdAsc();
    }
    @Override
    public User getUserByUserId(Integer userId) throws SQLException { // 특정조회 (userid기준, admin용)
        return userRepository.findById(userId).orElse(null);
    }
    @Override
    public UserDTO getUserSimpleInfoByUserId(Integer userId) throws SQLException {
        User user = userRepository.findById(userId).orElse(null);
        if(user != null) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserName(user.getUserName());
            userDTO.setUserNickname(user.getUserNickname());
            userDTO.setCreatedAt(user.getCreatedAt());
            userDTO.setUserType(user.getUserType());

            return userDTO;
        }
        return null;
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
    public void updateUsertype(Integer userId, Integer newTypeNum) {
        User userInf = userRepository.findById(userId).orElse(null);
        Usertype newUsertype = usertypeRepository.findById(newTypeNum).orElse(null);
        if(userInf != null && newUsertype != null) {
            userInf.setUserType(newUsertype);
            userRepository.save(userInf);
        }
    }
    @Override
    public void deleteUser(Integer userId) throws SQLException { // 회원삭제
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsersByUserType(int userTypeId) throws SQLException {
        return userRepository.findByUserTypeUserTypeId(userTypeId);
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
}

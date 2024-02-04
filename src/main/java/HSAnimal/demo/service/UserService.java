package HSAnimal.demo.service;

import HSAnimal.demo.DTO.*;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.domain.UserKeywords;
import HSAnimal.demo.enums.ErrorCode;
import HSAnimal.demo.exception.AccountAlreadyExistsException;
import HSAnimal.demo.exception.AccountNotFoundException;
import HSAnimal.demo.exception.EmailAlreadyExistsException;
import HSAnimal.demo.exception.WrongPasswordException;
import HSAnimal.demo.repository.UserKeywordsRepository;
import HSAnimal.demo.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.security.auth.login.LoginException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserKeywordsRepository userKeywordsRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                        UserKeywordsRepository userKeywordsRepository, TokenService tokenService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userKeywordsRepository = userKeywordsRepository;
        this.tokenService = tokenService;
    }

    public String signup(UserDto dto) {
        boolean userExists = userRepository.existsByUserId(dto.getUserId());
        boolean emailExists = userRepository.existsByEmail(dto.getEmail());
        if (userExists) {
            throw new AccountAlreadyExistsException("User ID already exists.");
        } else if (emailExists){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }

    public CreateAccessTokenDto login(UserDto userDTO){
        User user = authenticateUser(userDTO);
        tokenService.saveRefreshToken(user);
        return tokenService.createAccessToken(user);
    }

    public User authenticateUser(UserDto userDTO){
        User user = userRepository.findByUserId(userDTO.getUserId())
                .orElseThrow(() -> new AccountNotFoundException("User not found"));
        if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new WrongPasswordException("Wrong password");
        }
    }

    public void updateUser(String userId, UpdateUserDto updateUserDTO){
        userRepository.findByUserId(userId)
                .map(user -> {
                    user.changeName(updateUserDTO.getUsername());
                    user.changeEmail(updateUserDTO.getEmail());
                    user.changePassword(bCryptPasswordEncoder.encode(updateUserDTO.getPassword()));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new AccountNotFoundException("User not found"));
    }

    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new AccountNotFoundException("User not found"));
        userRepository.delete(user);
    }

    public void deleteUserKeywords(String userId, List<UserKeywordsDto> userKeywordsDtoList) {
        for (UserKeywordsDto userKeywordsDTO: userKeywordsDtoList) {
            int optionId = userKeywordsDTO.getOptionId();
            UserKeywords userKeywords = userKeywordsRepository.findByUserIdAndOptionId(userId, optionId)
                    .orElseThrow(() -> new AccountNotFoundException("User not found"));
            userKeywordsRepository.delete(userKeywords);
        }
    }
}

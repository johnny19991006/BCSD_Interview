package HSAnimal.demo.service;

import HSAnimal.demo.DTO.*;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.domain.UserKeywords;
import HSAnimal.demo.repository.UserKeywordsRepository;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    public String signup(UserDTO dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }

    public CreateAccessTokenDTO login(UserDTO userDTO){
        User user = authenticateUser(userDTO);
        tokenService.saveRefreshToken(user);
        return tokenService.createAccessToken(user);
    }

    public User authenticateUser(UserDTO userDTO){
        User user = userRepository.findByUserId(userDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 없습니다."));
        if (bCryptPasswordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO){
        userRepository.findByUserId(userId)
                .map(user -> {
                    user.changeName(updateUserDTO.getUsername());
                    user.changeEmail(updateUserDTO.getEmail());
                    user.changePassword(bCryptPasswordEncoder.encode(updateUserDTO.getPassword()));
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
    }

    public void deleteUser(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
        userRepository.delete(user);
    }

    public void deleteUserKeywords(String userId, List<UserKeywordsDTO> userKeywordsDTOList) {
        for (UserKeywordsDTO userKeywordsDTO: userKeywordsDTOList) {
            int optionId = userKeywordsDTO.getOptionId();
            UserKeywords userKeywords = userKeywordsRepository.findByUserIdAndOptionId(userId, optionId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."));
            userKeywordsRepository.delete(userKeywords);
        }
    }
}

package HSAnimal.demo.service;

import HSAnimal.demo.DTO.LoginResponseDTO;
import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.configuration.TokenProvider;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                        TokenProvider tokenProvider){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenProvider = tokenProvider;
    }

    // 회원가입
    public String signup(UserDTO dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }

    // 사용자 인증
    public String authenticateUser(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByUserId(userDTO.getUserId());
        if (userOptional.isPresent() && bCryptPasswordEncoder.matches(userDTO.getPassword(),
                userOptional.get().getPassword())) {
            LoginResponseDTO loginDTO = new LoginResponseDTO(tokenProvider
                    .generateToken(userOptional.get(), Duration.ofHours(2)));
            return loginDTO.getToken();
        }
        return "로그인 오류";
    }



    public User findById(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
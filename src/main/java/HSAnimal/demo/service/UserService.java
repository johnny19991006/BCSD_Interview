package HSAnimal.demo.service;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String signup(UserDTO dto) {
        User user = User.builder()
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
        return userRepository.save(user).getUserId();
    }

    // 회원가입 시 필요한 사용자 저장
    public Long save(UserDTO dto) {
        User user = User.builder()
                .username(dto.getUsername())
                .userId(dto.getUserId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();
        userRepository.save(user);
        return user.getId();
    }

    // 사용자 정보를 DB에서 조회 및 비교
    public boolean authenticateUser(String userId, String password) {
        Optional<User> userOptional = userRepository.findByUserId(userId);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String hashedPasswordFromDatabase = user.getPassword();
            if (bCryptPasswordEncoder.matches(password, hashedPasswordFromDatabase)) {
                return true;
            }
        }

        return false;
    }

    public User findById(String userId){
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
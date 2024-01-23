package HSAnimal.demo.service;

import HSAnimal.demo.DTO.UserDTO;
import HSAnimal.demo.entity.User;
import HSAnimal.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long save(UserDTO dto) {
        User user = User.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .build();

        userRepository.save(user);

        return user.getId();
    }
}
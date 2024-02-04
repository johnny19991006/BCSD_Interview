package HSAnimal.demo.service;

import HSAnimal.demo.DTO.CreateAccessTokenDto;
import HSAnimal.demo.DTO.UserDto;
import HSAnimal.demo.domain.User;
import HSAnimal.demo.exception.AccountAlreadyExistsException;
import HSAnimal.demo.exception.EmailAlreadyExistsException;
import HSAnimal.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SignService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final UserService userService;

    public SignService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                       TokenService tokenService, UserService userService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenService = tokenService;
        this.userService = userService;
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
        User user = userService.authenticateUser(userDTO);
        tokenService.saveRefreshToken(user);
        return tokenService.createAccessToken(user);
    }
}

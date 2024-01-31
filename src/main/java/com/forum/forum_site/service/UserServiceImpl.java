package com.forum.forum_site.service;

import com.forum.forum_site.domain.Role;
import com.forum.forum_site.domain.User;
import com.forum.forum_site.dto.ScrapPostDto;
import com.forum.forum_site.repository.RoleRepository;
import com.forum.forum_site.repository.ScrapRepository;
import com.forum.forum_site.repository.UserRepository;
import com.forum.forum_site.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ScrapRepository scrapRepository;
    private final RoleRepository roleRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User newUser, Role role) {
        newUser.setRole(role);
        return userRepo.save(newUser);
    }

    @Override
    public void updateUsername(Integer id, User user) {
        Optional<User> updateUser = userRepo.findById(id);

        updateUser.ifPresent(selectUser -> {
            selectUser.setUsername(user.getUsername());
            selectUser.setPassword(user.getPassword());

            userRepo.save(selectUser);
        });
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> updateUser = userRepo.findById(id);

        updateUser.ifPresent(selectUser -> {
            userRepo.delete(selectUser);
        });
    }

    @Override
    public void joinUser(Map<String, String> user) {
        Role defaultRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("기본 역할이 설정되지 않았습니다."));

        userRepo.save(User.builder()
                .email(user.get("email"))
                .password(passwordEncoder.encode(user.get("password")))
                .username(user.get("username"))
                .role(defaultRole)
                .build());
    }

    @Override
    public String loginUser(Map<String, String> user) {
        User member = userRepo.findByUsername(user.get("username"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 User 입니다."));
        if (!passwordEncoder.matches(user.get("password"), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(member.getUsername(), member.getRole());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScrapPostDto> getUserScrapList() {
        User currentUser = getCurrentAuthenticatedUser();
        // 스크랩된 포스트를 ScrapPostDto 리스트로 변환
        return scrapRepository.findAllByAuthor(currentUser).stream()
                .map(scrap -> new ScrapPostDto(
                        scrap.getPost().getId(),
                        scrap.getPost().getTitle(),
                        scrap.getPost().getContent(),
                        scrap.getPost().getCreated_at(),
                        scrap.getPost().getLikes_count()
                        // 필요한 경우 추가 필드 매핑
                ))
                .collect(Collectors.toList());
    }
}

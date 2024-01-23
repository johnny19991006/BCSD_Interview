package HSAnimal.demo.service;

import HSAnimal.demo.entity.User;
import HSAnimal.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.findByUserId(userId)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUserId(), "", getAuthorities(user)))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with userId: " + userId));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // 사용자의 권한 설정 로직
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }
}

package bcsd.backend.project.pokku.security;

import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserInfoDetailsService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findById(userid).orElseThrow(() -> new UsernameNotFoundException("Invalid authentication!"));
        return new CustomUserInfoDetails(userInfo);
    }
}

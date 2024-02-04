package bcsd.backend.project.pokku.security;

import bcsd.backend.project.pokku.dao.UserInfoRepository;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.exception.NoSuchDataException.NoSuchDataException;
import bcsd.backend.project.pokku.exception.ResCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUserInfoDetailsService implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String userid) throws RuntimeException {
        UserInfo userInfo = userInfoRepository.findById(userid)
                .orElseThrow(() -> new NoSuchDataException("존재하지 않는 사용자 입니다.", userid, ResCode.NO_SUCH_DATA.value()));
        return new CustomUserInfoDetails(userInfo);
    }
}

package bcsd.backend.project.pokku.service.Portfolio;

import bcsd.backend.project.pokku.dao.SkillsFrontendRepository;
import bcsd.backend.project.pokku.dao.UserPortfolioSkillsFrontendRepository;
import bcsd.backend.project.pokku.domain.SkillsFrontend;
import bcsd.backend.project.pokku.domain.UserInfo;
import bcsd.backend.project.pokku.domain.UserPortfolioSkillsFrontend;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioRequest;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final UserPortfolioSkillsFrontendRepository userPortfolioSkillsFrontendRepository;
    private final SkillsFrontendRepository skillsFrontendRepository;

    @Override
    public Boolean addSkills(PortfolioRequest request) throws Exception{

        if (request.getSkillsFrontendId() != null){
            skillsFrontendRepository.findById(request.getSkillsFrontendId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_frontend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsFrontendRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsFrontend.builder().skillsFontendId(request.getSkillsFrontendId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsFrontendRepository.save(UserPortfolioSkillsFrontend.builder()
                        .skillsFrontend(SkillsFrontend.builder().skillsFontendId(request.getSkillsFrontendId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }

        return true;
    }

    @Override
    public Boolean deleteSkills(PortfolioRequest request) throws Exception{
        UserPortfolioSkillsFrontend exists = userPortfolioSkillsFrontendRepository.findEntity(
                UserInfo.builder().userId(request.getUserId()).build(),
                SkillsFrontend.builder().skillsFontendId(request.getSkillsFrontendId()).build())
                .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

        userPortfolioSkillsFrontendRepository.deleteById(exists.getUserPortfolioSkillsFrontendId());

        return true;
    }

}

package bcsd.backend.project.pokku.service.Portfolio;

import bcsd.backend.project.pokku.dao.SkillsBackendRepository;
import bcsd.backend.project.pokku.dao.SkillsFrontendRepository;
import bcsd.backend.project.pokku.dao.UserPortfolioSkillsBackendRepository;
import bcsd.backend.project.pokku.dao.UserPortfolioSkillsFrontendRepository;
import bcsd.backend.project.pokku.domain.*;
import bcsd.backend.project.pokku.dto.Portfolio.PortfolioRequest;
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

    private final UserPortfolioSkillsBackendRepository userPortfolioSkillsBackendRepository;
    private final SkillsBackendRepository skillsBackendRepository;

    @Override
    public Boolean addSkills(PortfolioRequest request) throws Exception{

        if (request.getCategory().equals("frontend")){
            skillsFrontendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_frontend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsFrontendRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsFrontendRepository.save(UserPortfolioSkillsFrontend.builder()
                        .skillsFrontend(SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }else if(request.getCategory().equals("backend")){
            skillsBackendRepository.findById(request.getSkillsId())
                    .orElseThrow(() -> new Exception("존재하지 않는 skills_backend_id 입니다."));

            Optional<Long> exists = userPortfolioSkillsBackendRepository.findEntityCnt(
                    UserInfo.builder().userId(request.getUserId()).build(),
                    SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build());

            if(exists.get() == 0) {
                userPortfolioSkillsBackendRepository.save(UserPortfolioSkillsBackend.builder()
                        .skillsBackend(SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                        .userInfo(UserInfo.builder().userId(request.getUserId()).build())
                        .build());
            }
        }

        return true;
    }

    @Override
    public Boolean deleteSkills(PortfolioRequest request) throws Exception{
        if (request.getCategory().equals("frontend")) {
            UserPortfolioSkillsFrontend exists = userPortfolioSkillsFrontendRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsFrontend.builder().skillsFontendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsFrontendRepository.deleteById(exists.getUserPortfolioSkillsFrontendId());
        }else if(request.getCategory().equals("backend")) {
            UserPortfolioSkillsBackend exists = userPortfolioSkillsBackendRepository.findEntity(
                            UserInfo.builder().userId(request.getUserId()).build(),
                            SkillsBackend.builder().skillsBackendId(request.getSkillsId()).build())
                    .orElseThrow(() -> new BadCredentialsException("유효하지 않은 데이터 입니다."));

            userPortfolioSkillsBackendRepository.deleteById(exists.getUserPortfolioSkillsBackendId());
        }

        return true;
    }

}
